package jmu.zxyzg.final_project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jmu.zxyzg.final_project.pojo.Buyer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface BuyerMapper {

    @Insert("insert into buyer(name,pwd) values (#{name},#{pwd})")
    int register(Buyer buyer);

    @Select("select * from buyer where name=#{name}")
    Buyer findBuyerByName(String name);

    @Update("update buyer set pwd=#{pwd} where name=#{name}")
    void updatePwd(String name, String pwd);
}
