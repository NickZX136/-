package jmu.zxyzg.final_project.service.impl;

import jmu.zxyzg.final_project.mapper.BuyerMapper;
import jmu.zxyzg.final_project.mapper.BuyerReportMapper;
import jmu.zxyzg.final_project.mapper.OrderDetailMapper;
import jmu.zxyzg.final_project.pojo.Buyer;
import jmu.zxyzg.final_project.pojo.OrderDetail;
import jmu.zxyzg.final_project.service.BuyerReportService;
import jmu.zxyzg.final_project.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BuyerReportServiceImpl implements BuyerReportService {

    @Autowired
    private BuyerReportMapper buyerReportMapper;

    @Autowired
    private BuyerMapper buyerMapper;

    @Override
    public List<OrderDetail> generate(Integer sellerId, Integer reportYear, Integer reportMonth) {
        Map<String,Object> map = ThreadLocalUtil.get();
        String buyerName = (String) map.get("name");

        Buyer buyer = buyerMapper.findBuyerByName(buyerName);
        Integer buyerId = buyer.getId();

        return buyerReportMapper.searchOrderDetailForGenerate(buyerId, sellerId, reportYear, reportMonth);
    }
}
