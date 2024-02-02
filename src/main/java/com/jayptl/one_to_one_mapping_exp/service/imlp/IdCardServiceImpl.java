package com.jayptl.one_to_one_mapping_exp.service.imlp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jayptl.one_to_one_mapping_exp.exception.EntityNotFoundException;
import com.jayptl.one_to_one_mapping_exp.model.IdCard;
import com.jayptl.one_to_one_mapping_exp.repository.IdCardRepository;
import com.jayptl.one_to_one_mapping_exp.service.IdCardService;

@Service
public class IdCardServiceImpl implements IdCardService {

    @Autowired
    private IdCardRepository idCardRepository;

    @Override
    public List<IdCard> getAllIdCards() {
        return idCardRepository.findAll();
    }

    @Override
    public IdCard getIdCardById(long cardId) {
        return idCardRepository.findById(cardId)
                .orElseThrow(() -> new EntityNotFoundException("Entity With Id " + cardId + "Not Found"));
    }

    @Override
    public IdCard addNewIdCard(IdCard idCard) {
        return idCardRepository.save(idCard);
    }

    @Override
    public boolean deleteIdCardById(long cardId) {
        if (idCardRepository.existsById(cardId)) {
            idCardRepository.deleteById(cardId);
            return true;
        }
        return false;
    }

}
