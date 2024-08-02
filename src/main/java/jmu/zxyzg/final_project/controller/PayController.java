package jmu.zxyzg.final_project.controller;

import jmu.zxyzg.final_project.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(("/pay"))
public class PayController {

    @Autowired
    private PayService payService;

}
