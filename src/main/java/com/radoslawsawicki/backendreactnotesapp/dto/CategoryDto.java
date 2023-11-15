package com.radoslawsawicki.backendreactnotesapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CategoryDto {

    private Long id;
    private String categoryName;
    private String username;
}