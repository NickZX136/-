package jmu.zxyzg.final_project.controller;

import jmu.zxyzg.final_project.pojo.Result;
import jmu.zxyzg.final_project.pojo.Tag;
import jmu.zxyzg.final_project.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(("/tag"))
public class TagController {

    @Autowired
    private TagService tagService;

    @PostMapping("/add")
    public Result add(@RequestBody Tag tag) {
        tagService.add(tag);
        return Result.success();
    }

    public Result delete(@RequestParam("id") int id) {
        tagService.delete(id);
        return Result.success();
    }
}
