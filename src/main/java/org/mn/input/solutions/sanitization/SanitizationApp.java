package org.mn.input.solutions.sanitization;

import com.google.common.io.Resources;
import org.apache.commons.io.FilenameUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@SpringBootApplication
@RestController
public class SanitizationApp {

    public static void main(String[] args) {
        SpringApplication.run(SanitizationApp.class, args);
    }

    @GetMapping("/kunde")
    public ResponseEntity<String> getFile(String id) throws IOException {

        // Build file path
        String path = "Kunden/" + id + ".txt";

        // Normalize path to avoid path traversal attacks
        path = FilenameUtils.normalize(path);
        if (path == null) {
            return ResponseEntity.badRequest().body("Invalid id");
        }

        // Get file from resources folder
        File file = new File(Resources.getResource(path).getFile());

        // Read file
        String content = Files.readString(file.toPath());

        // Return content
        return ResponseEntity.ok(content);
    }

}