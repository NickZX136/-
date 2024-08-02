package jmu.zxyzg.final_project.service;

import jmu.zxyzg.final_project.pojo.Address;

import java.util.List;

public interface AddressService {
    void add(Address address);

    void delete(Integer id);

    void update(Address address);

    List<Address> searchAll();
}
