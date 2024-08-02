package jmu.zxyzg.final_project.mapper;

import jmu.zxyzg.final_project.pojo.Address;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AddressMapper {

    @Insert("insert into address(buyer_id, address, phone, contact_person) " +
            "VALUES (#{buyerId},#{address},#{phone},#{contactPerson})")
    void add(Address address);

    @Delete("delete from address where id=#{id}")
    void delete(Integer id);

    @Update("update address set buyer_id=#{buyerId}, address=#{address}, " +
            "phone=#{phone}, contact_person=#{contactPerson} where id=#{id}")
    void update(Address address);

    @Select("select * from address")
    List<Address> searchAll();
}
