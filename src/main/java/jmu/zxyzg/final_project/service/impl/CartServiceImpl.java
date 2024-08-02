package jmu.zxyzg.final_project.service.impl;

import jmu.zxyzg.final_project.dto.CartProductDTO;
import jmu.zxyzg.final_project.mapper.BuyerMapper;
import jmu.zxyzg.final_project.mapper.CartMapper;
import jmu.zxyzg.final_project.pojo.Buyer;
import jmu.zxyzg.final_project.pojo.Cart;
import jmu.zxyzg.final_project.service.CartService;
import jmu.zxyzg.final_project.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private BuyerMapper buyerMapper;

    @Override
    public void add(Cart cart) {
        cartMapper.add(cart);
    }

    @Override
    public void delete(int id) {
        cartMapper.delete(id);
    }

    @Override
    public void update(Integer id, Integer productNum, boolean checked) {
        cartMapper.update(id, productNum, checked);
    }

    @Override
    public List<CartProductDTO> searchAllByBuyerId() {
        Map<String,Object> map = ThreadLocalUtil.get();
        String buyerName = (String) map.get("name");
        Buyer buyer = buyerMapper.findBuyerByName(buyerName);
        Integer buyerId = buyer.getId();
        return cartMapper.searchAllByBuyerId(buyerId);
    }

    @Override
    public List<CartProductDTO> searchAllCheckByBuyerId(Integer buyerId) {
        return cartMapper.searchAllCheckByBuyerId(buyerId);
    }

    @Override
    public List<CartProductDTO> searchAllByBuyer() {
        Map<String,Object> map = ThreadLocalUtil.get();
        String name = (String) map.get("name");
        Buyer buyer = buyerMapper.findBuyerByName(name);
        return cartMapper.searchAllByBuyerId(buyer.getId());
    }

    @Override
    public void updateProductNum(Integer id, Integer productNum) {
        cartMapper.updateProductNum(id,productNum);
    }
}
