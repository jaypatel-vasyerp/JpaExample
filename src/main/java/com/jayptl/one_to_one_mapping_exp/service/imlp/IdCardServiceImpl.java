package com.jayptl.one_to_one_mapping_exp.service.imlp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jayptl.one_to_one_mapping_exp.dto.IdCardDto;
import com.jayptl.one_to_one_mapping_exp.dto.IdCardWithouConfInfo;
import com.jayptl.one_to_one_mapping_exp.exception.EntityNotFoundException;
import com.jayptl.one_to_one_mapping_exp.model.IdCard;
import com.jayptl.one_to_one_mapping_exp.repository.IdCardRepository;
import com.jayptl.one_to_one_mapping_exp.service.IdCardService;

@Service
public class IdCardServiceImpl implements IdCardService {

    @Autowired
    private IdCardRepository idCardRepository;

    @Override
    public List<IdCardWithouConfInfo> getAllIdCards() {
        List<IdCard> idCards = idCardRepository.findAll();
        List<IdCard> activeIdcards = new ArrayList<IdCard>();
        for (IdCard idCard : idCards) {
            if (!idCard.isDeleted()) {
                activeIdcards.add(idCard);
            }
        }
        List<IdCardWithouConfInfo> idCardDtos = new ArrayList<IdCardWithouConfInfo>();
        for (IdCard idCard : activeIdcards) {
            idCardDtos.add(idCardToIdCardWithoutConfInfo(idCard));
        }
        return idCardDtos;
    }

    @Override
    public IdCardDto getIdCardById(long cardId) {
        IdCard idCard = idCardRepository.findById(cardId)
                .orElseThrow(() -> new EntityNotFoundException("Entity With Id " + cardId + "Not Found"));
        if (idCard.isDeleted()) {
            return new IdCardDto(null, null, null);
        }
        return idCardToIdCardDto(idCard);
    }

    @Override
    public IdCardWithouConfInfo addNewIdCard(IdCardDto idCard) {
        IdCard savedIdCard = idCardDtoToIdCard(idCard);
        idCardToIdCardDto(idCardRepository.save(savedIdCard));
        return new IdCardWithouConfInfo(savedIdCard.getJobTitle(), savedIdCard.getDepartmentName());
    }

    @Override
    public boolean deleteIdCardById(long cardId) {
        if (idCardRepository.existsById(cardId)) {
            IdCard idCard = idCardRepository.findById(cardId).get();
            idCard.setDeleted(true);
            idCardRepository.save(idCard);
            return true;
        }
        return false;
    }

    private IdCard idCardDtoToIdCard(IdCardDto idCardDto){
        return new IdCard(0,false, idCardDto.getJobTitle(), idCardDto.getDepartmentName(),idCardDto.getConfidentialInfo());
    }

    private IdCardDto idCardToIdCardDto(IdCard idCard){
        return new IdCardDto(idCard.getJobTitle(), idCard.getDepartmentName(),idCard.getConfidentialInfo());
    }

    private IdCardWithouConfInfo idCardToIdCardWithoutConfInfo(IdCard idCard){
        return new IdCardWithouConfInfo(idCard.getJobTitle(), idCard.getDepartmentName()); 
    }

}
