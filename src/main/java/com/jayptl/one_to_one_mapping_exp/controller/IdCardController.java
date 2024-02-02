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

import com.jayptl.one_to_one_mapping_exp.model.IdCard;
import com.jayptl.one_to_one_mapping_exp.service.IdCardService;

@RestController
@RequestMapping("/api/idcard")
public class IdCardController {

    @Autowired
    private IdCardService idCardService;

    @GetMapping("id/{cardId}")
    public IdCard getIdCardById(@PathVariable(name = "cardId") long cardId) {
        return idCardService.getIdCardById(cardId);
    }

    @GetMapping("/all")
    public List<IdCard> getMethodName() {
        return idCardService.getAllIdCards();
    }

    @PostMapping("/add")
    public IdCard addNewCard(@RequestBody IdCard idCard) {
        return idCardService.addNewIdCard(idCard);
    }

    @DeleteMapping("/delete/{cardId}")
    public String deleteIdCardById(@PathVariable(name = "cardId") long cardId) {
        if (idCardService.deleteIdCardById(cardId)) {
            return "Deleted";
        }
        return "Failed";
    }

}
