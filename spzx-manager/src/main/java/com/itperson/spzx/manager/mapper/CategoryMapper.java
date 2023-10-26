package com.itperson.spzx.manager.mapper;

import com.itperson.spzx.model.entity.product.Category;
import com.itperson.spzx.model.vo.product.CategoryExcelVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    List<Category> selectCategoryByParentId(Long id);

    int selectCountByParentId(Long id);

    List<Category> findAll();

    void batchInsert(List<CategoryExcelVo> cachedDataList);

}
