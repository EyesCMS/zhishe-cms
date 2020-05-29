package edu.fzu.zhishe.core.service.impl;

import cn.hutool.core.util.StrUtil;
import edu.fzu.zhishe.common.exception.Asserts;
import edu.fzu.zhishe.common.exception.StorageException;
import edu.fzu.zhishe.common.exception.StorageFileNotFoundException;
import edu.fzu.zhishe.common.util.FileNameUtils;
import edu.fzu.zhishe.core.config.StorageProperties;
import edu.fzu.zhishe.core.service.StorageService;
import edu.fzu.zhishe.core.web.FileUploadController;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

/**
 * @author liang on 5/6/2020.
 * @version 1.0
 */
@Service
@Slf4j
public class StorageServiceImpl implements StorageService {

    @Value("${storage.path.str.image}")
    String imgPathStr = "images";
    @Value("${storage.path.str.file}")
    String filePathStr = "files";

    private final Path imageRootLocation;

    @Autowired
    public StorageServiceImpl(StorageProperties storageProperties) {
        this.imageRootLocation = Paths.get(storageProperties.getImageLocation());
    }

    @Override
    public String store(MultipartFile file, Path location) {
        if (file == null) {
            return null;
        }
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        String newFilename = FileNameUtils.getFileName(filename);
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + filename);
            }
            if (filename.contains(StrUtil.DOUBLE_DOT)) {
                // This is a security check
                throw new StorageException(
                    "Cannot store file with relative path outside current directory "
                        + filename);
            }

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, location.resolve(newFilename),
                    StandardCopyOption.REPLACE_EXISTING);
            }
        }
        catch (IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }

        // generate URL
        Resource resource = loadAsResource(newFilename, location);
        String methodName;

        if (location.toString().contains(imgPathStr)) {
            methodName = "serveImageFile";
        } else {

            if (location.toString().contains(filePathStr)) {
                methodName = "serveFile";
            } else {
                methodName = "";
                Asserts.fail("error file type");
            }
        }
        String serveFile = MvcUriComponentsBuilder
            .fromMethodName(FileUploadController.class, methodName, resource.getFilename())
            .build()
            .toUri()
            .toString();
        return serveFile;
    }

    @Override
    public String storeImage(MultipartFile file) {

        return this.store(file, imageRootLocation);
    }

    @Override
    public Stream<Path> loadAll(Path location) {
        try {
            return Files.walk(location, 1)
                .filter(path -> !path.equals(location))
                .map(location::relativize);
        }
        catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }
    }

    @Override
    public Path load(String filename, Path location) {
        return location.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename, Path location) {
        try {
            Path file = load(filename, location);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() && resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageFileNotFoundException("Could not read file: " + filename);
            }
        }
        catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public int deleteFile(Path location) {
        try {
            Files.deleteIfExists(location);
        } catch (IOException e) {
            throw new StorageException("Failed to delete file " + location, e);
        }
        return 1;
    }

    @Override
    public void deleteAll(Path location) {
        FileSystemUtils.deleteRecursively(location.toFile());
    }

    @Override
    public void init(List<Path> locations) {
        try {
            for (Path location : locations) {
                Files.createDirectories(location);
            }
        }
        catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }
}
