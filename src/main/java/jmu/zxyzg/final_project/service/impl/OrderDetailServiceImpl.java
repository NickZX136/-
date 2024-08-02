package jmu.zxyzg.final_project.service.impl;

import jmu.zxyzg.final_project.dto.OrderDetailProductSellerDTO;
import jmu.zxyzg.final_project.mapper.*;
import jmu.zxyzg.final_project.pojo.*;
import jmu.zxyzg.final_project.service.OrderDetailService;
import jmu.zxyzg.final_project.service.OrderTableService;
import jmu.zxyzg.final_project.utils.GetTimeUtil;
import jmu.zxyzg.final_project.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private OrderTableMapper orderTableMapper;

    @Autowired
    private BuyerMapper buyerMapper;

    @Autowired
    private SellerMapper sellerMapper;

    @Override
    public BigDecimal calculateOrderDetailPrice(OrderDetail orderDetail) {
        //这里调用product里的函数，得到product的价格，乘上数量得到price。
        Product product = productMapper.searchById(orderDetail.getProductId());

        BigDecimal productPrice = new BigDecimal(String.valueOf(product.getPrice()));
        BigDecimal productNum = new BigDecimal(orderDetail.getProductNum());

        BigDecimal result1 = productPrice.multiply(productNum);
        BigDecimal result2 = result1.add(orderDetail.getPostage());

        return result2;
    }

    @Override
    public List<OrderDetailProductSellerDTO> searchByOrderTableId(Integer orderTableId) {
        return orderDetailMapper.searchByOrderTableId(orderTableId);
    }

    @Override
    public void updateStatus(Integer id, Integer status) {
        orderDetailMapper.updateStatus(id, status);
    }

    @Override
    public void delete(Integer id) {
        orderDetailMapper.delete(id);
    }

    @Override
    public void updateOrderTableId(Integer id, Integer orderTableId) {
        orderDetailMapper.updateOrderTableId(id, orderTableId);
    }

    @Override
    public OrderDetail searchTheLastOneByBuyerId(Integer buyerId) {
        return orderDetailMapper.searchTheLastOneByBuyerId(buyerId);
    }

    @Override
    public OrderDetail searchById(Integer id) {
        return orderDetailMapper.searchById(id);
    }

    @Override
    public void updateStatusToPaid(Integer id) {
        //让订单对应的商品销量增加
        OrderDetail orderDetail = searchById(id);
        productMapper.addSales(orderDetail.getProductId(), orderDetail.getProductNum());
        //更新OrderDetail状态
        updateStatus(id, 2);
    }

    @Override
    public void updateStatusToReceived(Integer id) {
        updateStatus(id, 4);
    }

    @Override
    public void updateStatusToShipped(Integer id) {
        System.out.println(id);
        updateStatus(id, 3);
    }

    @Override
    public List<OrderDetailProductSellerDTO> searchLastOrderDetails() {
        Map<String,Object> map = ThreadLocalUtil.get();
        String name = (String) map.get("name");
        Buyer buyer = buyerMapper.findBuyerByName(name);
        Integer buyerId = buyer.getId();
        OrderTable orderTable = orderTableMapper.searchTheLastOneByBuyerId(buyerId);
        return searchByOrderTableId(orderTable.getId());
    }

    @Override
    public List<OrderDetailProductSellerDTO> searchBySellerId() {
        Map<String,Object> map = ThreadLocalUtil.get();
        String name = (String) map.get("name");
        Seller seller = sellerMapper.findSellerByName(name);
        return orderDetailMapper.searchBySellerId(seller.getId());
    }

    @Override
    public List<OrderDetailProductSellerDTO> searchByBuyerId() {
        Map<String,Object> map = ThreadLocalUtil.get();
        String name = (String) map.get("name");
        Buyer buyer = buyerMapper.findBuyerByName(name);
        return orderDetailMapper.searchByBuyerId(buyer.getId());
    }

    @Override
    public void add(OrderDetail orderDetail) {
        orderDetailMapper.add(orderDetail);

    }




}
