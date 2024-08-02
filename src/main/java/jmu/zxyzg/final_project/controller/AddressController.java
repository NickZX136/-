package jmu.zxyzg.final_project.controller;

import jmu.zxyzg.final_project.pojo.Address;
import jmu.zxyzg.final_project.pojo.Result;
import jmu.zxyzg.final_project.service.AddressService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(("/address"))
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("/add")
    public Result add(@RequestBody Address address) {
        addressService.add(address);
        return Result.success();
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam("id") Integer id) {
        addressService.delete(id);
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody Address address) {
        addressService.update(address);
        return Result.success();
    }

    @GetMapping("/searchAll")
    public Result<List<Address>> searchAll() {
        List<Address> list = addressService.searchAll();
        return Result.success(list);
    }
}
