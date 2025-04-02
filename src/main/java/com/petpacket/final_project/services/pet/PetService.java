package com.petpacket.final_project.services.pet;

import com.petpacket.final_project.entities.pet.Pet;
import com.petpacket.final_project.entities.user.User;
import com.petpacket.final_project.repository.pet.PetRepository;
import com.petpacket.final_project.repository.user.UserRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PetService {
//    private final String UPLOAD_DIR = "C:\\Users\\quoct\\Desktop\\image";
    private final String UPLOAD_DIR = "C:\\Users\\quoct\\Desktop\\capstone\\new_source\\fe-pet-service\\public\\img\\pets";
    
    @Autowired
    private PetRepository petRepository;

    @Autowired
    private UserRepository userRepository;

    public Pet addPet(Integer userId, Pet pet){
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            pet.setPetOwner(user);  // Assign the user to the pet
            return petRepository.save(pet);
        } else {
            throw new RuntimeException("User not found with ID: " + userId);
        }
    }

    public List<Pet> getAllPetsByUserId(Integer userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return petRepository.findAllByPetOwner(user);  // Assuming you have a method in PetRepository
        } else {
            throw new RuntimeException("User not found with ID: " + userId);
        }
    }

    public String savePetImage(MultipartFile file) throws IOException {
        // Create the upload directory if it doesn't exist
        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);  // Ensure the upload directory exists
        }

        // Get the file name and create the path for the file
        String fileName = file.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);

        // Save the file to the server
        Files.copy(file.getInputStream(), filePath);

        // Return the file path (relative to the upload directory)
        return filePath.toString();
    }
}
