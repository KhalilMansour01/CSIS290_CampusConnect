package com.example.campus_connect.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    private final Path uploadDir = Paths.get("uploads"); // Local folder

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        System.out.println("File upload request received.");
        System.out.println("File name: " + file.getOriginalFilename());
        try {
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir); // Create folder if not exists
            }

            String originalFilename = file.getOriginalFilename();
            if (originalFilename == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Invalid file: filename is missing.");
            }
            String filename = StringUtils.cleanPath(originalFilename);
            Path targetPath = uploadDir.resolve(filename);

            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

            return ResponseEntity.ok("File uploaded successfully: " + filename);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to upload file: " + e.getMessage());
        }
    }

    @GetMapping("/test")
    public ResponseEntity<String> testUploadAccess() {
        return ResponseEntity.ok("Upload access works");
    }

}