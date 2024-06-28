package org.mn.input.solutions.denylist;

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
public class DenylistApp {

    public static void main(String[] args) {
        SpringApplication.run(DenylistApp.class, args);
    }

    @GetMapping("/kunde")
    public ResponseEntity<String> getFile(String id) throws IOException {

        // Reject path traversal attacks
        // NOT RECOMMENDED
        // Denylists are usually not recommended, because it is hard to maintain a complete list of forbidden characters
        if (id.contains("../")) {
            return ResponseEntity.badRequest().body("Invalid id");
        }

        // Build file path
        String path = "Kunden/" + id + ".txt";
        File file = new File(Resources.getResource(path).getFile());
        String content = Files.readString(file.toPath());

        // Return content
        return ResponseEntity.ok(content);
    }

}