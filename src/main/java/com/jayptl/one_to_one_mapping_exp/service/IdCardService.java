package com.jayptl.one_to_one_mapping_exp.service;

import java.util.List;

import com.jayptl.one_to_one_mapping_exp.model.IdCard;

public interface IdCardService {

    List<IdCard> getAllIdCards();

    IdCard getIdCardById(long cardId);

    IdCard addNewIdCard(IdCard idCard);

    boolean deleteIdCardById(long cardId);

}
