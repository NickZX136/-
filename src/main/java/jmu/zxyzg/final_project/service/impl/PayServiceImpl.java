package jmu.zxyzg.final_project.service.impl;

import jmu.zxyzg.final_project.mapper.PayMapper;
import jmu.zxyzg.final_project.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayServiceImpl implements PayService {

    @Autowired
    private PayMapper payMapper;

}
