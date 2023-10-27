package com.itperson.spzx.manager.service;

import com.github.pagehelper.PageInfo;
import com.itperson.spzx.model.entity.product.Brand;

import java.util.List;

public interface BrandService {

    //列表
    PageInfo<Brand> findByPage(Integer page, Integer limit);

    //添加
    void save(Brand brand);

    //查询所有品牌
    List<Brand> findAll();

    void update(Brand brand);

}
