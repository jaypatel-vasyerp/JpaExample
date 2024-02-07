package com.jayptl.one_to_one_mapping_exp.service.imlp;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jayptl.one_to_one_mapping_exp.service.FileService;

@Service
public class FileServiceImpl implements FileService {

    private static final String DIR_URL = "C:\\Users\\jaypatel\\Desktop\\Upload";

    @Override
    public String uploadFile(MultipartFile file, String mode) {

        switch (mode) {
            case "Create":
                try {
                    File directory = new File(DIR_URL);

                    if (!directory.exists()) {
                        directory.mkdirs();
                    }

                    String fileName = file.getOriginalFilename();

                    Path filePath = Paths.get(DIR_URL).resolve(fileName);

                    Files.write(filePath, file.getBytes(), StandardOpenOption.CREATE);

                    String fileDownloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                            .path("/api/file/download/")
                            .path(fileName).toUriString();

                    return "File Uploaded Successfully Download Url :" + fileDownloadUrl;
                } catch (Exception e) {
                    return "Could not upload file" + e.getMessage();
                }

            case "Append":
                try {
                    File directory = new File(DIR_URL);

                    if (!directory.exists()) {
                        directory.mkdirs();
                    }

                    String fileName = "Mainfile.txt";

                    Path filePath = Paths.get(DIR_URL).resolve(fileName);

                    // Creates file if does not exists

                    if (!Files.exists(Paths.get(DIR_URL).resolve(fileName))) {
                        File file2 = new File(filePath.toString());
                        file2.createNewFile();
                    }

                    Files.write(filePath, file.getBytes(), StandardOpenOption.APPEND);

                    Files.deleteIfExists(Paths.get(DIR_URL).resolve(file.getOriginalFilename()));

                } catch (Exception e) {
                    e.getMessage();
                }
                return "file Appended to main file";

            default:
                return "Provide Correct Request Param.";
        }

    }

    @Override
    public ResponseEntity<Object> downloadFile(String fileName) {
        ResponseEntity<Object> response = null;
        try {

            Path filePath = Paths.get(DIR_URL).resolve(fileName).normalize();

            File file = filePath.toFile();

            if (file.exists()) {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                headers.setContentDispositionFormData("attachment", fileName);
                response = ResponseEntity.ok().headers(headers).contentLength(file.length())
                        .body(new FileSystemResource(file));
            }

        } catch (Exception e) {
            response = ResponseEntity.notFound().build();
        }

        return response;
    }

    @Override
    public String deleteFileByName(String fileName) {
        try {
            Path path = Paths.get(DIR_URL).resolve(fileName).normalize();
            if (Files.deleteIfExists(path)) {
                return "deleted";
            }

            return "File Does Not Exists";

        } catch (Exception e) {
            return "Exception";
        }

    }
}
