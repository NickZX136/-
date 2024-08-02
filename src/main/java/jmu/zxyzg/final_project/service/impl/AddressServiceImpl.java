package jmu.zxyzg.final_project.service.impl;

import jmu.zxyzg.final_project.mapper.AddressMapper;
import jmu.zxyzg.final_project.pojo.Address;
import jmu.zxyzg.final_project.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public void add(Address address) {
        addressMapper.add(address);
    }

    @Override
    public void delete(Integer id) {
        addressMapper.delete(id);
    }

    @Override
    public void update(Address address) {
        addressMapper.update(address);
    }

    @Override
    public List<Address> searchAll() {
        return addressMapper.searchAll();
    }

}
