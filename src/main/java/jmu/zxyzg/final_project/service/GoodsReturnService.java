package jmu.zxyzg.final_project.service;

import jmu.zxyzg.final_project.pojo.GoodsReturn;
import jmu.zxyzg.final_project.pojo.OrderDetail;

import java.util.List;

public interface GoodsReturnService {
    void add(GoodsReturn goodsReturn);

    List<GoodsReturn> searchBySellerId(int sellerId);

    void agreeReturn(Integer id, String sellerOpinion);

    void refuseReturn(Integer id, String sellerOpinion);
}
