package jmu.zxyzg.final_project.service;

import jmu.zxyzg.final_project.pojo.OrderDetail;
import jmu.zxyzg.final_project.pojo.OrderTable;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

public interface OrderTableService {
    void add(OrderTable orderTable);

    void delete(Integer id);

    void addFromCart(OrderTable orderTable) throws ParseException;

    void addFromProductPage(OrderDetail orderDetail) throws ParseException;

    OrderTable searchTheLastOneByBuyerId(int buyerId);

    void submit(Integer id);

    void updateStatus(Integer id, Integer status);

    OrderTable searchById(Integer id);

    void updateCreateTime(Integer id, Date createTime);

    void updateStatusToPaid(Integer id);
}
