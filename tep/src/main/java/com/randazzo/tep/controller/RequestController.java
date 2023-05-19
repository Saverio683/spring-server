package com.randazzo.tep.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.randazzo.tep.model.EClass;
import com.randazzo.tep.model.ESection;
import com.randazzo.tep.model.EStatus;
import com.randazzo.tep.model.Request;
import com.randazzo.tep.model.User;
import com.randazzo.tep.payload.CreateRequestBody;
import com.randazzo.tep.payload.UpdateRequestBody;
import com.randazzo.tep.repository.RequestRepository;
import com.randazzo.tep.repository.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/requests")
public class RequestController {
    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public ResponseEntity<?> getAllRequests() {
        List<Request> requests = requestRepository.findAll();

        if(requests.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok().body(requests);
    }

    @PutMapping("/update") 
    public ResponseEntity<?> updateRequest(@Valid @RequestBody UpdateRequestBody data) {
        try {
            Optional<Request> requestFind = requestRepository.findById(data.getRequestId());
            Optional<User> userFind = userRepository.findById(data.getUserId());
            if(requestFind.isPresent() && userFind.isPresent()) {
                Request request = requestFind.get();
                User user = userFind.get();
                EStatus response;
                if(data.isAccepted()) {
                    response = EStatus.STATUS_APPROVED;
                } else {
                    response = EStatus.STATUS_REJECTED;
                }

                switch(user.getRole()) {
                    case ROLE_PROFESSORE:
                        request.setApprovedByProfessore(response);
                        break;
                    case ROLE_VICEPRESIDE:
                        request.setApprovedByVicepreside(response);
                        break;
                    default:
                        return ResponseEntity.badRequest().body("Error: invalid user role!");
                }
                requestRepository.save(request);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();     
            }
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/new")
	public ResponseEntity<?> newRequest(@Valid @RequestBody CreateRequestBody request) {
        try {
                EClass classe;
                ESection section;

                switch(request.getClasse()) {
                    case "i":
                        classe = EClass.CLASS_I;
                        break;
                    case "ii":
                        classe = EClass.CLASS_II;
                        break;
                    case "iii":
                        classe = EClass.CLASS_III;
                        break;
                    case "iv":
                        classe = EClass.CLASS_IV;
                        break;
                    case "v":
                        classe = EClass.CLASS_V;
                        break;
                    default:
                        return ResponseEntity.badRequest().body("Error: invalid class!");
                }

                switch(request.getSection()) {
                    case "a":
                        section = ESection.SECTION_A;
                        break;
                    case "b":
                        section = ESection.SECTION_B;
                        break;
                    case "c":
                        section = ESection.SECTION_C;
                        break;
                    case "d":
                        section = ESection.SECTION_D;
                        break;
                    case "e":
                        section = ESection.SECTION_E;
                        break;
                    case "f":
                        section = ESection.SECTION_F;
                        break;
                    case "g":
                        section = ESection.SECTION_G;
                        break;
                    case "h":
                        section = ESection.SECTION_H;
                        break;
                    default:
                        return ResponseEntity.badRequest().body("Error: invalid section!");
                }

                requestRepository.save(new Request(
                    null, 
                    request.getStartHour(),
                    request.getEndHour(),
                    request.getDate(),  
                    classe, 
                    section, 
                    EStatus.STATUS_PENDING,
                    EStatus.STATUS_PENDING
                ));

                return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
	}
}
