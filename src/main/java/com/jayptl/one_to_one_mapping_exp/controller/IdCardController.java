package com.jayptl.one_to_one_mapping_exp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jayptl.one_to_one_mapping_exp.dto.IdCardDto;
import com.jayptl.one_to_one_mapping_exp.dto.IdCardWithouConfInfo;
import com.jayptl.one_to_one_mapping_exp.dto.ResponseDto;
import com.jayptl.one_to_one_mapping_exp.service.IdCardService;

@RestController
@RequestMapping("/api/idcard")
public class IdCardController {

    @Autowired
    private IdCardService idCardService;

    @GetMapping("id/{cardId}")
    public ResponseDto getIdCardById(@PathVariable(name = "cardId") long cardId) {
        ResponseDto responseDto = new ResponseDto();
        IdCardDto idCardDto = idCardService.getIdCardById(cardId);
        responseDto.setStatus(200);
        responseDto.setMessage("Ok");
        responseDto.setData(idCardDto);
        return responseDto;
    }

    @GetMapping("/all")
    public ResponseDto getAllIdCards() {
        List<IdCardWithouConfInfo> idcards = idCardService.getAllIdCards();
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus(200);
        responseDto.setMessage("Ok");
        responseDto.setData(idcards);
        return responseDto;
    }

    @PostMapping("/add")
    public ResponseDto addNewCard(@RequestBody IdCardDto idCardDto) {
        IdCardWithouConfInfo idCardWithouConfInfo = idCardService.addNewIdCard(idCardDto);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setData(idCardWithouConfInfo);
        responseDto.setMessage("Ok");
        responseDto.setStatus(200);
        ;
        return responseDto;
    }

    @DeleteMapping("/delete/{cardId}")
    public ResponseDto deleteIdCardById(@PathVariable(name = "cardId") long cardId) {
        ResponseDto responseDto = new ResponseDto();

        if (idCardService.deleteIdCardById(cardId)) {
            responseDto.setData("Deleted Success");
            responseDto.setMessage("Ok");
            responseDto.setStatus(200);
        } else {
            responseDto.setData("Id card with id " + cardId + " not exists");
            responseDto.setMessage("Not Found");
            responseDto.setStatus(404);
        }
        return responseDto;

    }

}
