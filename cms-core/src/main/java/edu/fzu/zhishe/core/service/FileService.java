package edu.fzu.zhishe.core.service;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author liang on 5/6/2020.
 * @version 1.0
 */
public interface FileService {

    String saveAvatarFile(MultipartFile file) throws IOException;
}
