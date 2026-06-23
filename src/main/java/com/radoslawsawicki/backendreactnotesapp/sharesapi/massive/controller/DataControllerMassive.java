package com.radoslawsawicki.backendreactnotesapp.sharesapi.massive.controller;

import com.radoslawsawicki.backendreactnotesapp.sharesapi.massive.client.SharesApiConfigMassive;
import com.radoslawsawicki.backendreactnotesapp.sharesapi.massive.dto.DataMassiveDto;
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
public class DataControllerMassive {

    private final SharesApiConfigMassive sharesApiConfig;

    @GetMapping("/shares/one")
    public ResponseEntity<DataMassiveDto> getData1() {
        try {
            DataMassiveDto sharesLists = sharesApiConfig.getDates("I:NDX");
            return ResponseEntity.ok(sharesLists);
        } catch (Exception e) {
            throw new SharesProcessingException("Error getData1");
        }
    }

    @GetMapping("/shares/two")
    public ResponseEntity<DataMassiveDto> getData2() {
        try {
            DataMassiveDto sharesLists = sharesApiConfig.getDates("AAAU");
            return ResponseEntity.ok(sharesLists);
        } catch (Exception e) {
            throw new SharesProcessingException("Error getData2");
        }
    }

    @GetMapping("/shares/three")
    public ResponseEntity<DataMassiveDto> getData3() {
        try {
            DataMassiveDto sharesLists = sharesApiConfig.getDates("NVDA");
            return ResponseEntity.ok(sharesLists);
        } catch (Exception e) {
            throw new SharesProcessingException("Error getData3");
        }
    }
}