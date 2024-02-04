package com.jayptl.one_to_one_mapping_exp.service;

import java.util.List;

import com.jayptl.one_to_one_mapping_exp.dto.IdCardDto;
import com.jayptl.one_to_one_mapping_exp.dto.IdCardWithouConfInfo;

public interface IdCardService {

    List<IdCardWithouConfInfo> getAllIdCards();

    IdCardDto getIdCardById(long cardId);

    IdCardWithouConfInfo addNewIdCard(IdCardDto idCard);

    boolean deleteIdCardById(long cardId);

}
