package com.radoslawsawicki.backendreactnotesapp.controller;

import com.radoslawsawicki.backendreactnotesapp.domain.Category;
import com.radoslawsawicki.backendreactnotesapp.dto.CategoryDto;
import com.radoslawsawicki.backendreactnotesapp.exception.CategoryNotFoundException;
import com.radoslawsawicki.backendreactnotesapp.mapper.CategoryMapper;
import com.radoslawsawicki.backendreactnotesapp.service.CategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notes")
@Tag(
		name = "CRUD REST APIs for category",
		description = "CRUD REST APIs to CREATE, UPDATE and FETCH category "
)
public class CategoryController {

	private final CategoryMapper mapper;
	private final CategoryService service;

	@GetMapping("/category")
	public ResponseEntity<List<CategoryDto>> getCategories () {
		List<Category> categories = service.getAllCategories();
		return ResponseEntity.ok(mapper.mapToCategoryDtoList(categories));
	}

	@GetMapping(value = "/category/{id}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Long id) throws CategoryNotFoundException {
		return ResponseEntity.ok(mapper.mapToCategoryDto(service.getCategory(id)));
	}

	@PutMapping(value = "/category", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto) {
		service.saveCategory(mapper.mapToCategory(categoryDto));
		return ResponseEntity.ok().build();
	}

	@PostMapping(value = "/category", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
		service.saveCategory(mapper.mapToCategory(categoryDto));
		return ResponseEntity.ok().build();
	}
}