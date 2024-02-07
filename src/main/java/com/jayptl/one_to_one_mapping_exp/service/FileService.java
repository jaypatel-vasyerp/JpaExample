package com.jayptl.one_to_one_mapping_exp.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    String uploadFile(MultipartFile file, String mode);

    ResponseEntity<Object> downloadFile(String fileName);

    String deleteFileByName(String fileName);
}
