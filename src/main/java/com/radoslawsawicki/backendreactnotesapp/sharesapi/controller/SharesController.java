package com.radoslawsawicki.backendreactnotesapp.sharesapi.controller;

import com.radoslawsawicki.backendreactnotesapp.sharesapi.client.SharesApiConfig;
import com.radoslawsawicki.backendreactnotesapp.sharesapi.dto.SharesDto;
import com.radoslawsawicki.backendreactnotesapp.sharesapi.exception.SharesProcessingException;
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
        name = "CRUD REST APIs for shares",
        description = "CRUD REST APIs to CREATE, UPDATE, FETCH and DELETE shares"
)
public class SharesController {

    private final SharesApiConfig sharesApiConfig;

    @GetMapping("/data/one")
    public ResponseEntity<SharesDto> getData1() throws SharesProcessingException {
        SharesDto sharesLists = sharesApiConfig.getShares("IXIC.INDX");
        return ResponseEntity.ok(sharesLists);
    }

    @GetMapping("/data/two")
    public ResponseEntity<SharesDto> getData2() throws SharesProcessingException {
        SharesDto sharesLists = sharesApiConfig.getShares("US500.INDX");
        return ResponseEntity.ok(sharesLists);
    }

    @GetMapping("/data/three")
    public ResponseEntity<SharesDto> getData3() throws SharesProcessingException {
        SharesDto sharesLists = sharesApiConfig.getShares("WIG20.INDX");
        return ResponseEntity.ok(sharesLists);
    }

    @GetMapping("/data/four")
    public ResponseEntity<SharesDto> getData4() throws SharesProcessingException {
        SharesDto sharesLists = sharesApiConfig.getShares("NVDA");
        return ResponseEntity.ok(sharesLists);
    }

    @GetMapping("/data/five")
    public ResponseEntity<SharesDto> getData5() throws SharesProcessingException {
        SharesDto sharesLists = sharesApiConfig.getShares("GOOG");
        return ResponseEntity.ok(sharesLists);
    }

    @GetMapping("/data/six")
    public ResponseEntity<SharesDto> getData6() throws SharesProcessingException {
        SharesDto sharesLists = sharesApiConfig.getShares("MSFT");
        return ResponseEntity.ok(sharesLists);
    }
}