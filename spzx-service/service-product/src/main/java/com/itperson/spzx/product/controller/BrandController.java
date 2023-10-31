package com.itperson.spzx.product.controller;

import com.itperson.spzx.model.entity.product.Brand;
import com.itperson.spzx.model.vo.common.Result;
import com.itperson.spzx.model.vo.common.ResultCodeEnum;
import com.itperson.spzx.product.service.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/api/product/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @Operation(summary = "获取全部品牌")
    @GetMapping("findAll")
    public Result findAll() {
        List<Brand> list = brandService.findAll();
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }
}
