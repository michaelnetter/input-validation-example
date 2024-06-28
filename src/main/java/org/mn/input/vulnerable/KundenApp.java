package org.mn.input.vulnerable;

import com.google.common.io.Resources;
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
public class KundenApp {

    public static void main(String[] args) {
        SpringApplication.run(KundenApp.class, args);
    }

    @GetMapping("/kunde")
    // Receive input from user
    public ResponseEntity<String> getFile(String id) throws IOException {

        // Build file path
        String path = "Kunden/" + id + ".txt";

        // Get file from resources folder
        File file = new File(Resources.getResource(path).getFile());

        // Read file
        String content = Files.readString(file.toPath());

        // Return content
        return ResponseEntity.ok(content);
    }

}