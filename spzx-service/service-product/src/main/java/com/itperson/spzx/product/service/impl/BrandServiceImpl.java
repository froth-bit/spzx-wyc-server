package com.itperson.spzx.product.service.impl;

import com.itperson.spzx.model.entity.product.Brand;
import com.itperson.spzx.product.mapper.BrandMapper;
import com.itperson.spzx.product.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    //获取全部品牌
    @Override
    public List<Brand> findAll() {
        return brandMapper.findAll();
    }
}
