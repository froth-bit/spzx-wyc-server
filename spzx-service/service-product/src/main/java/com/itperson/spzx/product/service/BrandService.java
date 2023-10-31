package com.itperson.spzx.product.service;


import com.itperson.spzx.model.entity.product.Brand;

import java.util.List;

public interface BrandService {

    //获取全部品牌
    List<Brand> findAll();
}
