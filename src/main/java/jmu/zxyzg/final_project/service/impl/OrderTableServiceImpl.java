package jmu.zxyzg.final_project.service.impl;

import jmu.zxyzg.final_project.dto.CartProductDTO;
import jmu.zxyzg.final_project.dto.OrderDetailProductSellerDTO;
import jmu.zxyzg.final_project.mapper.OrderTableMapper;
import jmu.zxyzg.final_project.pojo.*;
import jmu.zxyzg.final_project.service.*;
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
public class OrderTableServiceImpl implements OrderTableService {

    @Autowired
    private OrderTableMapper orderTableMapper;

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private BuyerService buyerService;

    @Override
    public void add(OrderTable orderTable) {
        orderTableMapper.add(orderTable);
    }

    @Override
    public void delete(Integer id) {
        List<OrderDetailProductSellerDTO> list = orderDetailService.searchByOrderTableId(id);
        for(OrderDetailProductSellerDTO orderDetail : list) {
            orderDetailService.delete(orderDetail.getId());
        }
        orderTableMapper.delete(id);
    }

    @Override
    public void addFromCart(OrderTable orderTable) throws ParseException {


        /* 用线程获取当前用户id，调用add函数传入用户id新建一个orderTable对象并返回orderTable的id，
            用id获取当前用户的购物车内容，购物车那需要再写一个获取购物车中打钩的商品的内容，
        *   然后对这些商品内容一一新建orderDetail对象，这些对象都传入orderTable的id。 */
        Map<String,Object> map = ThreadLocalUtil.get();
        String buyerName = (String) map.get("name");
        Buyer buyer = buyerService.findBuyerByName(buyerName);
        Integer buyerId = buyer.getId();

        //新建一个orderTable对象
        orderTable.setBuyerId(buyerId);
        orderTable.setFlag(1);
        orderTableMapper.add(orderTable);

        //返回orderTable的id
        OrderTable orderTable1 = orderTableMapper.searchTheLastOneByBuyerId(buyerId);
        Integer id = orderTable1.getId();

        //用id获取当前用户的购物车内容
        List<CartProductDTO> list = cartService.searchAllCheckByBuyerId(buyerId);

        //totalPrice
        BigDecimal totalPrice = BigDecimal.ZERO;

        //获取创建时间
        Date createTime = GetTimeUtil.getTime();

        for(CartProductDTO cartProductDTO : list) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setBuyerId(buyerId);
            orderDetail.setProductId(cartProductDTO.getProductId());
            orderDetail.setOrderTableId(id);
            orderDetail.setSellerId(cartProductDTO.getSellerId());

            //获取订单总价result2
            Product product = productService.searchById(cartProductDTO.getProductId());
            BigDecimal productPrice = new BigDecimal(String.valueOf(product.getPrice()));
            BigDecimal productNum = new BigDecimal(cartProductDTO.getProductNum());
            BigDecimal result1 = productPrice.multiply(productNum);
            BigDecimal result2 = result1.add(product.getPostage());
            totalPrice = totalPrice.add(result2);

            orderDetail.setOrderDetailPrice(result2);
            orderDetail.setProductNum(cartProductDTO.getProductNum());
            orderDetail.setStatus(0);
            orderDetail.setPostage(product.getPostage());
            orderDetail.setCreateDate(createTime);

            orderDetailService.add(orderDetail);
//            productService.addSales(cartProductDTO.getProductId(),cartProductDTO.getProductNum());
        }

        //更新当前orderTable的totalPrice值
        orderTableMapper.updateTotalPrice(totalPrice, id);
        orderTableMapper.updateCreateTime(id, createTime);
        submit(id);
    }

    @Override
    public void addFromProductPage(OrderDetail orderDetail) throws ParseException {
        Map<String,Object> map = ThreadLocalUtil.get();
        String name = (String) map.get("name");
        Buyer buyer = buyerService.findBuyerByName(name);
        orderDetail.setBuyerId(buyer.getId());
        //在OrderDetail生成订单
        //计算订单总价
        BigDecimal result2 = orderDetailService.calculateOrderDetailPrice(orderDetail);

        //获取创建时间
        Date createTime = GetTimeUtil.getTime();

        orderDetail.setCreateDate(createTime);
        orderDetail.setOrderDetailPrice(result2);

        orderDetailService.add(orderDetail);

        //在OrderTable生成订单
        OrderTable orderTable = new OrderTable();
        orderTable.setAddressId(orderDetail.getAddressId());
        orderTable.setTotalPrice(result2);
        orderTable.setBuyerId(orderDetail.getBuyerId());
        orderTable.setStatus(orderDetail.getStatus());
        Date time = GetTimeUtil.getTime();
        orderTable.setCreateTime(time);
        orderTable.setFlag(2);
        orderTable.setCreateTime(createTime);
        orderTableMapper.add(orderTable);

        OrderTable orderTable1 = orderTableMapper.searchTheLastOneByBuyerId(orderDetail.getBuyerId());
        OrderDetail orderDetail1 = orderDetailService.searchTheLastOneByBuyerId(orderDetail.getBuyerId());
        orderDetailService.updateOrderTableId(orderDetail1.getId(), orderTable1.getId());

        submit(orderTable1.getId());
    }

    @Override
    public OrderTable searchTheLastOneByBuyerId(int buyerId) {
        return orderTableMapper.searchTheLastOneByBuyerId(buyerId);
    }

    @Override
    public void submit(Integer id) {
        //改变所有订单详情状态为1，并增加订单详情对应的物品数量
        List<OrderDetailProductSellerDTO> list = orderDetailService.searchByOrderTableId(id);
        for(OrderDetailProductSellerDTO orderDetail : list) {
            orderDetailService.updateStatus(orderDetail.getId(),1);
            productService.addSales(orderDetail.getProductId(),orderDetail.getProductNum());
        }

        //改变订单状态为1
        orderTableMapper.updateStatus(id,1);
    }

    @Override
    public OrderTable searchById(Integer id){
        return orderTableMapper.searchById(id);
    }

    @Override
    public void updateCreateTime(Integer id, Date createTime) {
        orderTableMapper.updateCreateTime(id, createTime);
    }

    @Override
    public void updateStatusToPaid(Integer id) {
        orderTableMapper.updateStatusToPaid(id);
        //让订单对应的商品销量增加
        OrderTable orderTable = searchById(id);
        if(orderTable.getFlag() == 1) {
            //用id获取当前用户的购物车内容
            List<CartProductDTO> list = cartService.searchAllCheckByBuyerId(orderTable.getBuyerId());
            for(CartProductDTO cartProductDTO : list) {
                productService.addSales(cartProductDTO.getProductId(),cartProductDTO.getProductNum());
            }
            //更新OrderDetail中的状态
            List<OrderDetailProductSellerDTO> orderDetailList = orderDetailService.searchByOrderTableId(id);
            for(OrderDetailProductSellerDTO orderDetail : orderDetailList) {
                orderDetailService.updateStatus(orderDetail.getId(),2);
            }

        }else {
            List<OrderDetailProductSellerDTO> orderDetail = orderDetailService.searchByOrderTableId(orderTable.getId());
            for(OrderDetailProductSellerDTO orderDetail1 : orderDetail) {
                productService.addSales(orderDetail1.getProductId(),orderDetail1.getProductNum());
                orderDetailService.updateStatus(orderDetail1.getId(),2);
            }
        }
    }

    @Override
    public void updateStatus(Integer id, Integer status) {
        orderTableMapper.updateStatus(id,status);
    }


}
