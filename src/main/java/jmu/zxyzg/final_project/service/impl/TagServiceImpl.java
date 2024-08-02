package jmu.zxyzg.final_project.service.impl;

import jmu.zxyzg.final_project.mapper.TagMapper;
import jmu.zxyzg.final_project.pojo.Tag;
import jmu.zxyzg.final_project.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public void add(Tag tag) {
        tagMapper.add(tag);
    }

    @Override
    public void delete(int id) {
        tagMapper.delete(id);
    }
}
