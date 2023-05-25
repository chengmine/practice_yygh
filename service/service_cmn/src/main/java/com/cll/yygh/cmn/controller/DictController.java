package com.cll.yygh.cmn.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cll.yygh.cmn.service.DictService;
import com.cll.yygh.common.result.Result;
import com.mysql.jdbc.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author chengll
 * @Date 2023/5/19 15:10
 * @Desc DictController
 */
@Api("数据字典管理")
@RestController
@RequestMapping("/admin/cmn/dict")
public class DictController {
    @Autowired
    private DictService service;

    @GetMapping("findChildData/{id}")
    @ApiOperation("根据父ID查询子数据列表")
    public Result findChildData(@PathVariable Long id) {
        return Result.ok(service.findChildData(id));
    }

    @GetMapping("exportDict")
    public Result exportDict(HttpServletResponse response) throws IOException {
        service.exportDict(response);
        return Result.ok();
    }

    @PostMapping("importDict")
    public Result importDict(MultipartFile file) throws IOException {
        service.importDict(file);
        return Result.ok();
    }
}
