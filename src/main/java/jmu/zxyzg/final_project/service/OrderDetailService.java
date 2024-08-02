package jmu.zxyzg.final_project.service;

import jmu.zxyzg.final_project.dto.OrderDetailProductSellerDTO;
import jmu.zxyzg.final_project.pojo.OrderDetail;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface OrderDetailService {

    void add(OrderDetail orderDetail);

    BigDecimal calculateOrderDetailPrice(OrderDetail orderDetail);

    List<OrderDetailProductSellerDTO> searchByOrderTableId(Integer orderTableId);

    void updateStatus(Integer id, Integer status);

    void delete(Integer id);

    void updateOrderTableId(Integer id, Integer orderTableId);

    OrderDetail searchTheLastOneByBuyerId(Integer buyerId);

    OrderDetail searchById(Integer id);

    void updateStatusToPaid(Integer id);

    void updateStatusToReceived(Integer id);

    void updateStatusToShipped(Integer id);

    List<OrderDetailProductSellerDTO> searchLastOrderDetails();

    List<OrderDetailProductSellerDTO> searchBySellerId();

    List<OrderDetailProductSellerDTO> searchByBuyerId();
}
