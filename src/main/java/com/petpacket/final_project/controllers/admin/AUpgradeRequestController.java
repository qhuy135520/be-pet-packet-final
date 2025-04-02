package com.petpacket.final_project.controllers.admin;

import com.petpacket.final_project.dto.response.SuccessResponse;
import com.petpacket.final_project.entities.UpgradeRequest;
import com.petpacket.final_project.entities.user.User;
import com.petpacket.final_project.services.UpgradeRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/upgrades")
public class AUpgradeRequestController {

    @Autowired
    private UpgradeRequestService upgradeRequestService;

    @GetMapping
    public ResponseEntity<?> getAllUpgradeRequest(){
        List<UpgradeRequest> listOfUpgradeRequest = upgradeRequestService.getAllUpgradeRequest();
        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setMessage("Fetch list of upgrade request successfully");
        successResponse.setData(listOfUpgradeRequest);
        successResponse.setStatusCode(HttpStatus.OK.value());
        return ResponseEntity.ok(successResponse);
    }

    @PutMapping("/confirm/{id}")
    public ResponseEntity<?> confirmRequest(@PathVariable Integer id){
        UpgradeRequest upgradeRequest = upgradeRequestService.confirmRequest(id);
        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setMessage("Confirmed request successfully with Id: " + id);
        successResponse.setData(upgradeRequest);
        successResponse.setStatusCode(HttpStatus.OK.value());
        return ResponseEntity.ok(successResponse);
    }

    @PutMapping("/reject/{id}")
    public ResponseEntity<?> rejectRequest(@PathVariable Integer id){
        UpgradeRequest upgradeRequest = upgradeRequestService.rejectRequest(id);
        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setMessage("Rejected request successfully with Id: " + id);
        successResponse.setData(upgradeRequest);
        successResponse.setStatusCode(HttpStatus.OK.value());
        return ResponseEntity.ok(successResponse);
    }

}
