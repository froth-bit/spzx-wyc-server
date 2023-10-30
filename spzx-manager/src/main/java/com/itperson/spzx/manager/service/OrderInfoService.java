package com.itperson.spzx.manager.service;


import com.itperson.spzx.model.dto.order.OrderStatisticsDto;
import com.itperson.spzx.model.vo.order.OrderStatisticsVo;

public interface OrderInfoService {

    OrderStatisticsVo getOrderStatisticsData(OrderStatisticsDto orderStatisticsDto);
}
