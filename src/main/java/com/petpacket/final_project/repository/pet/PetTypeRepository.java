package com.petpacket.final_project.repository.pet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petpacket.final_project.entities.pet.PetType;

@Repository
public interface PetTypeRepository extends JpaRepository<PetType, Integer> {
}
