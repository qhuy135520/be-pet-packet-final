package com.petpacket.final_project.repository.pet;

import com.petpacket.final_project.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petpacket.final_project.entities.pet.Pet;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer> {
    List<Pet> findAllByPetOwner(User user); 
}
