package com.itperson.spzx.product.controller;

import com.itperson.spzx.model.entity.product.Category;
import com.itperson.spzx.model.vo.common.Result;
import com.itperson.spzx.model.vo.common.ResultCodeEnum;
import com.itperson.spzx.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/api/product/category")
//@CrossOrigin //跨域
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //查询所有分类，树形封装
    @GetMapping("/findCategoryTree")
    public Result findCategoryTree() {
        List<Category> list = categoryService.findCategoryTree();
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }
}