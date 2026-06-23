package com.radoslawsawicki.backendreactnotesapp.sharesapi.marketstack.controller;

import com.radoslawsawicki.backendreactnotesapp.sharesapi.marketstack.dto.SharesDto;
import com.radoslawsawicki.backendreactnotesapp.sharesapi.marketstack.exception.SharesProcessingException;
import com.radoslawsawicki.backendreactnotesapp.sharesapi.marketstack.client.SharesApiConfig;
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
        name = "CRUD REST APIs for shares - MARKETSTACK",
        description = "CRUD REST APIs to CREATE, UPDATE, FETCH and DELETE shares"
)
public class SharesController {

    private final SharesApiConfig sharesApiConfig;

    //Applies to the BASIC plan
    @GetMapping("/shares/oneB")
    public ResponseEntity<SharesDto> getData1() {
        try {
            SharesDto sharesLists = sharesApiConfig.getShares("IXIC.INDX");
            return ResponseEntity.ok(sharesLists);
        } catch (Exception e) {
            throw new SharesProcessingException("Error getData1");
        }
    }

    //Applies to the BASIC plan
    @GetMapping("/shares/twoB")
    public ResponseEntity<SharesDto> getData2() {
        try {
            SharesDto sharesLists = sharesApiConfig.getShares("US500.INDX");
            return ResponseEntity.ok(sharesLists);
        } catch (Exception e) {
            throw new SharesProcessingException("Error getData2");
        }
    }

    //Applies to the BASIC plan
    @GetMapping("/shares/threeB")
    public ResponseEntity<SharesDto> getData3() {
        try {
            SharesDto sharesLists = sharesApiConfig.getShares("WIG20.INDX");
            return ResponseEntity.ok(sharesLists);
        } catch (Exception e) {
            throw new SharesProcessingException("Error getData3");
        }
    }
}