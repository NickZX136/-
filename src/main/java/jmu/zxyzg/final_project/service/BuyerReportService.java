package jmu.zxyzg.final_project.service;

import jmu.zxyzg.final_project.pojo.OrderDetail;

import java.util.List;

public interface BuyerReportService {
    List<OrderDetail> generate(Integer sellerId, Integer reportYear, Integer reportMonth);
}
