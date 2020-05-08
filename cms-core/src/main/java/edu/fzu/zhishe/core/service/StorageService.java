package edu.fzu.zhishe.core.service;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

	void init(List<Path> locations);

	String store(MultipartFile file, Path location);

	Stream<Path> loadAll(Path location);

	Path load(String filename, Path location);

	Resource loadAsResource(String filename, Path location);

	void deleteAll(Path location);
}
