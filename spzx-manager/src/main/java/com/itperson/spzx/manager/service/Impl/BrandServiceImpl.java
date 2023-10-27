package com.itperson.spzx.manager.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itperson.spzx.manager.mapper.BrandMapper;
import com.itperson.spzx.manager.service.BrandService;
import com.itperson.spzx.model.entity.product.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    //列表
    @Override
    public PageInfo<Brand> findByPage(Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        List<Brand> list = brandMapper.findByPage();
        PageInfo<Brand> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    //添加
    @Override
    public void save(Brand brand) {
        brandMapper.save(brand);
    }

    //查询所有品牌
    @Override
    public List<Brand> findAll() {
        return brandMapper.findByPage();
    }

    @Override
    public void update(Brand brand) {
        brandMapper.update(brand);
    }
}
