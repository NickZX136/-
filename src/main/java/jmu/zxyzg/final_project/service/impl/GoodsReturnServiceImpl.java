package jmu.zxyzg.final_project.service.impl;

import jmu.zxyzg.final_project.mapper.GoodsReturnMapper;
import jmu.zxyzg.final_project.mapper.OrderDetailMapper;
import jmu.zxyzg.final_project.mapper.ProductMapper;
import jmu.zxyzg.final_project.pojo.GoodsReturn;
import jmu.zxyzg.final_project.pojo.OrderDetail;
import jmu.zxyzg.final_project.service.GoodsReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsReturnServiceImpl implements GoodsReturnService {

    @Autowired
    private GoodsReturnMapper goodsReturnMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public void add(GoodsReturn goodsReturn) {

        OrderDetail orderDetail = orderDetailMapper.searchById(goodsReturn.getOrderDetailId());

        //判断商品是否发货
        if(orderDetail.getStatus() != 3){
            orderDetailMapper.updateStatus(goodsReturn.getOrderDetailId(), -2);
            goodsReturnMapper.add(goodsReturn);
            GoodsReturn goodsReturn1 = searchTheLastOneByBuyerId(goodsReturn.getBuyerId());
            updateStatus(goodsReturn1.getId(), 2);
            //商品销量减少
            productMapper.decreaseSales(orderDetail.getProductId(), orderDetail.getProductNum());
        }else {
            goodsReturnMapper.add(goodsReturn);
        }

    }

    @Override
    public List<GoodsReturn> searchBySellerId(int sellerId) {
        return goodsReturnMapper.searchBySellerId(sellerId);
    }

    @Override
    public void agreeReturn(Integer id, String sellerOpinion) {
        GoodsReturn goodsReturn = goodsReturnMapper.searchById(id);
        //设置订单详情状态
        orderDetailMapper.updateStatus(goodsReturn.getOrderDetailId(), -2);
        //商品数量减少
        OrderDetail orderDetail = orderDetailMapper.searchById(goodsReturn.getOrderDetailId());
        productMapper.decreaseSales(orderDetail.getProductId(), orderDetail.getProductNum());
        //设置退货单状态
        updateStatus(id, 1);
        updateSellerOpinion(id, sellerOpinion);
    }

    @Override
    public void refuseReturn(Integer id, String sellerOpinion) {
        GoodsReturn goodsReturn = goodsReturnMapper.searchById(id);
        //设置退货单状态
        updateStatus(id, -1);
        updateSellerOpinion(id, sellerOpinion);
    }

    public GoodsReturn searchById(Integer id) {
        return goodsReturnMapper.searchById(id);
    }

    public void updateStatus(Integer id, Integer status) {
        goodsReturnMapper.updateStatus(id, status);
    }

    public void updateSellerOpinion(Integer id, String sellerOpinion) {
        goodsReturnMapper.updateSellerOpinion(id, sellerOpinion);
    }

    public GoodsReturn searchTheLastOneByBuyerId(Integer buyerId){
        return goodsReturnMapper.searchTheLastOneByBuyerId(buyerId);
    }

}
