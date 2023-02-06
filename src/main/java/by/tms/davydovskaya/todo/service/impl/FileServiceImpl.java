package by.tms.davydovskaya.todo.service.impl;

import by.tms.davydovskaya.todo.controller.UploadController;
import by.tms.davydovskaya.todo.dtos.UploadResult;
import by.tms.davydovskaya.todo.exceptions.UploadFailedException;
import by.tms.davydovskaya.todo.service.FileService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileServiceImpl implements FileService {

    private static final Path RELATIVE_PATH = Path.of(".");

    @Override
    public UploadResult upload(MultipartFile file) {
        Path destinationFile = RELATIVE_PATH.resolve(
                Paths.get(file.getOriginalFilename()))
                .normalize().toAbsolutePath();

        try(InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, destinationFile,
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new UploadFailedException(String.format("Cannot upload file: %s", e.getMessage()));
        }

        return UploadResult.builder().message("Uploaded successfully").build();
    }

    @Override
    public List<String> loadAll() {
        try {
            return Files.walk(RELATIVE_PATH, 1)
                    .filter(path -> !path.equals(RELATIVE_PATH))
                    .map(RELATIVE_PATH::relativize)
                    .map(path -> MvcUriComponentsBuilder.fromMethodName(UploadController.class,
                                    "serveFile", path.getFileName().toString()).build().toUri().toString())
                    .collect(Collectors.toList());
        } catch(IOException e) {
            throw new UploadFailedException(String.format("Failed to read stored files: %s", e.getMessage()));
        }
    }

    @Override
    public Path load(String filename) {
        return RELATIVE_PATH.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new UploadFailedException("Could not read file: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new UploadFailedException("Could not read file: " + filename);
        }
    }
}
