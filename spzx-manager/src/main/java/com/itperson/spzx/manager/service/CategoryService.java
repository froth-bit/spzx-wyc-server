package com.itperson.spzx.manager.service;

import com.itperson.spzx.model.entity.product.Category;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CategoryService {
    void importData(MultipartFile file);

    void exportData(HttpServletResponse response);

    List<Category> findCategoryList(Long id);

}
