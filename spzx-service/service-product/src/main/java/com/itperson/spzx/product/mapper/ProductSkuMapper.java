package com.itperson.spzx.product.mapper;

import com.itperson.spzx.model.dto.h5.ProductSkuDto;
import com.itperson.spzx.model.entity.product.ProductSku;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductSkuMapper {
    //2 根据销量排序，获取前10条记录
    List<ProductSku> selectProductSkuBySale();

    //分页查询
    List<ProductSku> findByPage(ProductSkuDto productSkuDto);

    //2 根据skuId获取sku信息
    ProductSku getById(Long skuId);

    //根据商品id获取商品所有sku列表
    List<ProductSku> findByProductId(Long productId);
}
