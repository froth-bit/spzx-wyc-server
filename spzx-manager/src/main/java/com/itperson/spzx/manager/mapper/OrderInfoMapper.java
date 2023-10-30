package com.itperson.spzx.manager.mapper;

import com.itperson.spzx.model.entity.order.OrderStatistics;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderInfoMapper {
    //统计前一天交易金额
    OrderStatistics selectStatisticsByDate(String createDate);
}
