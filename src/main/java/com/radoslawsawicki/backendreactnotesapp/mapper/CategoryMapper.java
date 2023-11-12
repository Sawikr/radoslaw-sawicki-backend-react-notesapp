package com.radoslawsawicki.backendreactnotesapp.mapper;

import com.radoslawsawicki.backendreactnotesapp.domain.Category;
import com.radoslawsawicki.backendreactnotesapp.dto.CategoryDto;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryMapper {

    public Category mapToCategory(final CategoryDto categoryDto) {
        return new Category(
                categoryDto.getId(),
                categoryDto.getCategoryName()
        );
    }

    public CategoryDto mapToCategoryDto(final Category category) {
        return new CategoryDto(
                category.getId(),
                category.getCategoryName()
        );
    }

    public List<CategoryDto> mapToCategoryDtoList(final List<Category> category) {
        return category.stream()
                .map(this::mapToCategoryDto)
                .toList();
    }
}
