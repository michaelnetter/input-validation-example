package org.mn.input.solutions.allowlist;

import com.google.common.io.Resources;
import org.apache.commons.lang3.StringUtils;
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
public class AllowlistApp {

    public static void main(String[] args) {
        SpringApplication.run(AllowlistApp.class, args);
    }

    @GetMapping("/kunde")
    public ResponseEntity<String> getFile(String id) throws IOException {

        // Validate id, only allow non-empty
        if(StringUtils.isBlank(id) || !StringUtils.isNumeric(id)) {
            return ResponseEntity.badRequest().body("Invalid id");
        }

        // Validate that id is between 1 and 3
        int idAsInt = Integer.parseInt(id);
        if(idAsInt < 1 || idAsInt > 3) {
            return ResponseEntity.badRequest().body("Invalid id");
        }

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