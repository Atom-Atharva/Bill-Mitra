package in.billmitra.service;

import in.billmitra.io.CategoryRequest;
import in.billmitra.io.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse add(CategoryRequest request);

    List<CategoryResponse> read();

    void delete(String categoryId);
}
