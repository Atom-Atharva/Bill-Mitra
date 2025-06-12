package in.billmitra.service.impl;

import in.billmitra.entity.CategoryEntity;
import in.billmitra.io.CategoryRequest;
import in.billmitra.io.CategoryResponse;
import in.billmitra.repository.CategoryRepository;
import in.billmitra.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryResponse add(CategoryRequest request) {
        // Create Category
        CategoryEntity category = convertToEntity(request);

        // Save to DB
        category = categoryRepository.save(category);

        // Create Category Response
        return convertToResponse(category);
    }


    @Override
    public List<CategoryResponse> read() {
        return categoryRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String categoryId) {
        CategoryEntity existingCategory = categoryRepository.findByCategoryId(categoryId)
                .orElseThrow(()-> new RuntimeException("Category not found: "+ categoryId));

        categoryRepository.delete(existingCategory);
    }

    // Using @Builder
    private CategoryResponse convertToResponse(CategoryEntity category) {
        return CategoryResponse.builder()
                .name(category.getName())
                .description(category.getDescription())
                .bgColor(category.getBgColor())
                .imgUrl(category.getImgUrl())
                .categoryId(category.getCategoryId())
                .createdAt(category.getCreatedAt())
                .updatedAt(category.getUpdatedAt())
                .build();
    }

    // Using @Builder
    private CategoryEntity convertToEntity(CategoryRequest request) {
        return CategoryEntity.builder()
                .categoryId(UUID.randomUUID().toString())
                .name(request.getName())
                .description(request.getDescription())
                .bgColor(request.getBgColor())
                .build();
    }
}
