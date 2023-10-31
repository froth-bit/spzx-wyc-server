package com.itperson.spzx.product.service;

import com.github.pagehelper.PageInfo;
import com.itperson.spzx.model.dto.h5.ProductSkuDto;
import com.itperson.spzx.model.entity.product.ProductSku;
import com.itperson.spzx.model.vo.h5.ProductItemVo;

import java.util.List;

public interface ProductService {

    //2 根据销量排序，获取前10条记录
    List<ProductSku> selectProductSkuBySale();

    //分页查询
    PageInfo<ProductSku> findByPage(Integer page, Integer limit, ProductSkuDto productSkuDto);

    //商品详情接口
    ProductItemVo item(Long skuId);

    //远程调用：根据skuId返回sku信息
    ProductSku getBySkuId(Long skuId);
}
