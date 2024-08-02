package jmu.zxyzg.final_project.service;

import jmu.zxyzg.final_project.dto.CartProductDTO;
import jmu.zxyzg.final_project.pojo.Cart;

import java.util.List;

public interface CartService {
    void add(Cart cart);

    void delete(int id);

    void update(Integer id, Integer productNum, boolean checked);

    List<CartProductDTO> searchAllByBuyerId();

    List<CartProductDTO> searchAllCheckByBuyerId(Integer buyerId);

    List<CartProductDTO> searchAllByBuyer();

    void updateProductNum(Integer id, Integer productNum);
}
