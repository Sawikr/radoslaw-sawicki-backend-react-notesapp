package com.radoslawsawicki.backendreactnotesapp.sharesapi.massive.controller;

import com.radoslawsawicki.backendreactnotesapp.sharesapi.massive.client.SharesApiConfigMassive;
import com.radoslawsawicki.backendreactnotesapp.sharesapi.massive.dto.SharesMassiveDto;
import com.radoslawsawicki.backendreactnotesapp.sharesapi.massive.exception.SharesProcessingException;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notes")
@Tag(
        name = "CRUD REST APIs for shares - MASSIVE",
        description = "CRUD REST APIs to CREATE, UPDATE, FETCH and DELETE shares"
)
public class SharesControllerMassive {

    private final SharesApiConfigMassive sharesApiConfig;

    @GetMapping("/shares/oneS")
    public ResponseEntity<SharesMassiveDto> getData1() {
        try {
            SharesMassiveDto sharesLists = sharesApiConfig.getShares("I:NDX");
            return ResponseEntity.ok(sharesLists);
        } catch (Exception e) {
            throw new SharesProcessingException("Error getData1");
        }
    }

    @GetMapping("/shares/twoS")
    public ResponseEntity<SharesMassiveDto> getData2() {
        try {
            SharesMassiveDto sharesLists = sharesApiConfig.getShares("I:SPX");
            return ResponseEntity.ok(sharesLists);
        } catch (Exception e) {
            throw new SharesProcessingException("Error getData2");
        }
    }

    @GetMapping("/shares/threeS")
    public ResponseEntity<SharesMassiveDto> getData3() {
        try {
            SharesMassiveDto sharesLists = sharesApiConfig.getShares("I:COMP");
            return ResponseEntity.ok(sharesLists);
        } catch (Exception e) {
            throw new SharesProcessingException("Error getData3");
        }
    }
}