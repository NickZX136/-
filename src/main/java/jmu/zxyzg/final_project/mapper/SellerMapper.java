package jmu.zxyzg.final_project.mapper;

import jmu.zxyzg.final_project.pojo.Seller;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface SellerMapper {
    @Select("select * from seller where name=#{name}")
    Seller findSellerByName(String name);

    @Insert("insert into seller(name,pwd) values (#{name},#{pwd})")
    int register(Seller seller);

    @Update("update seller set pwd=#{pwd} where name=#{name}")
    void updatePwd(String name, String pwd);

    @Update("update seller set trade_name=#{tradeName} where name=#{name}")
    void updateTradeName(String name, String tradeName);

    @Select("select * from seller where id=#{id}")
    List<Seller> searchById(Integer id);

    @Select("select * from seller where trade_name like concat('%',#{tradeName},'%')")
    List<Seller> searchByTradeName(String tradeName);

    @Update("update seller set trade_name=#{tradeName}, description=#{description}, phone=#{phone}, contact_person=#{contactPerson} " +
            "where id=#{id}")
    void updateInfo(Seller seller);
}
