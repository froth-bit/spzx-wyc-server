package com.itperson.spzx.manager.controller;

import com.github.pagehelper.PageInfo;
import com.itperson.spzx.manager.service.BrandService;
import com.itperson.spzx.model.entity.product.Brand;
import com.itperson.spzx.model.vo.common.Result;
import com.itperson.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/admin/product/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    //查询所有品牌
    @GetMapping("/findAll")
    public Result findAll() {
        List<Brand> list = brandService.findAll();
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }

    //列表
    @GetMapping("/{page}/{limit}")
    public Result list(@PathVariable Integer page,
                       @PathVariable Integer limit) {
        PageInfo<Brand> pageInfo = brandService.findByPage(page,limit);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    //添加
    @PostMapping("/save")
    public Result save(@RequestBody Brand brand) {
        if (brand.getId()==null){
            brandService.save(brand);
        }else {
            brandService.update(brand);
        }
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

}
