package com.petpacket.final_project.services.pet;

import com.petpacket.final_project.entities.pet.PetType;
import com.petpacket.final_project.repository.pet.PetTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetTypeService {

    @Autowired
    private PetTypeRepository petTypeRepository;

    public List<PetType> getAllPetType(){
        return petTypeRepository.findAll();
    }
}
