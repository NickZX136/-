package jmu.zxyzg.final_project.mapper;

import jmu.zxyzg.final_project.pojo.Tag;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TagMapper {

    @Insert("insert into tag(name, parent_tag_id) values (#{name},#{parentTagId})")
    void add(Tag tag);

    @Delete("delete from tag where id=#{id}")
    void delete(int id);
}
