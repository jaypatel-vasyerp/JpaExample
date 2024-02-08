package com.jayptl.one_to_one_mapping_exp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jayptl.one_to_one_mapping_exp.dto.ResponseDto;
import com.jayptl.one_to_one_mapping_exp.service.FileService;

@RestController
@RequestMapping("/api/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @GetMapping("/download/{fileName}")
    public ResponseEntity<Object> downloadFileByName(@PathVariable String fileName) {
        return fileService.downloadFile(fileName);
    }

    @PostMapping("/upload")
    public ResponseDto uploadFileOrAppendFile(@RequestParam MultipartFile file, @RequestParam String mode) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("Ok");
        responseDto.setStatus(200);
        responseDto.setData(fileService.uploadFile(file, mode));
        return responseDto;
    }

    @DeleteMapping("/delete")
    public ResponseDto deleteFileByName(@RequestParam String filename) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setData(fileService.deleteFileByName(filename));
        responseDto.setStatus(200);
        responseDto.setMessage("Ok");
        return responseDto;
    }
}
