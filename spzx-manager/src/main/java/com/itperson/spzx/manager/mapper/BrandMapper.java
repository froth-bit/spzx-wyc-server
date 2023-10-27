package com.itperson.spzx.manager.mapper;

import com.itperson.spzx.model.entity.product.Brand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BrandMapper {
    //列表
    List<Brand> findByPage();

    //添加
    void save(Brand brand);

    void update(Brand brand);

}
