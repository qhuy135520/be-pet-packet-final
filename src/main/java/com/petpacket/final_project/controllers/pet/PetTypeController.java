package com.petpacket.final_project.controllers.pet;

import com.petpacket.final_project.dto.response.SuccessResponse;
import com.petpacket.final_project.services.pet.PetTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/types")
public class PetTypeController {
    @Autowired
    private PetTypeService petTypeService;

    @GetMapping
    public ResponseEntity<?> getAllPetType() {
        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setData(petTypeService.getAllPetType());
        successResponse.setMessage("Fetch ok");
        successResponse.setStatusCode(HttpStatus.OK.value());
        return ResponseEntity.ok(successResponse);
    }

}
