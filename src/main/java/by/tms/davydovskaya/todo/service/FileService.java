package by.tms.davydovskaya.todo.service;

import by.tms.davydovskaya.todo.dtos.UploadResult;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;

public interface FileService {

    UploadResult upload(MultipartFile file);
    List<String> loadAll();
    Path load(String filename);
    Resource loadAsResource(String filename);
}
