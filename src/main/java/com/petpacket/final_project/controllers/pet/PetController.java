package com.petpacket.final_project.controllers.pet;

import com.petpacket.final_project.dto.response.SuccessResponse;
import com.petpacket.final_project.entities.pet.Pet;
import com.petpacket.final_project.services.pet.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/pets")
public class PetController {

    @Autowired
    private PetService petService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getAllPetsByUserId(@PathVariable Integer userId) {
        SuccessResponse successResponse = new SuccessResponse();
        List<Pet> pets = petService.getAllPetsByUserId(userId);
        successResponse.setData(pets);
        successResponse.setMessage("All pets retrieved for user with ID: " + userId);
        successResponse.setStatusCode(HttpStatus.OK.value());
        return ResponseEntity.ok(successResponse);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<?> addPet(@PathVariable Integer userId, @RequestBody Pet pet) {
        SuccessResponse successResponse = new SuccessResponse();
        Pet newPet = petService.addPet(userId, pet);
        successResponse.setData(newPet);
        successResponse.setMessage("Pet added successfully");
        successResponse.setStatusCode(HttpStatus.OK.value());
        return ResponseEntity.ok(successResponse);
    }

    @PostMapping("/add/image")
    public ResponseEntity<?> addImage(@RequestParam("image") MultipartFile image) {
        SuccessResponse successResponse = new SuccessResponse();

        try {
            // Save the image and get the path
            String imagePath = petService.savePetImage(image);
            System.out.println("Path of image: " + imagePath);
            // Set the image path to the pet object (assuming the Pet class has a field for image path)

            successResponse.setData("Oke");
            successResponse.setMessage("Pet added successfully");
            successResponse.setStatusCode(HttpStatus.OK.value());
            return ResponseEntity.ok(successResponse);

        } catch (Exception e) {
            successResponse.setMessage("Error uploading image or adding pet");
            successResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(successResponse);
        }
    }

}
