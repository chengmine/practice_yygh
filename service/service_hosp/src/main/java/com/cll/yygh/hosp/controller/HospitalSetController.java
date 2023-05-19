package com.cll.yygh.hosp.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cll.yygh.common.result.Result;
import com.cll.yygh.entity.hosp.HospitalSet;
import com.cll.yygh.hosp.service.HospitalSetService;
import com.cll.yygh.vo.hosp.HospitalSetQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.security.provider.MD5;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * @Author chengll
 * @Date 2023/4/9 22:15
 * @Desc HospitalSetController
 */
@RestController
@RequestMapping("/admin/hosp/hospitalSet")
@Api(tags = "医院设置管理")
public class HospitalSetController {
    @Autowired
    private HospitalSetService hospitalSetService;

    @GetMapping("findAll")
    @ApiOperation("获取所有医院设置")
    public Result<List<HospitalSet>> findAllHospitalSet() {
        return Result.ok(hospitalSetService.list());
    }

    @DeleteMapping("/removeHospitalSet/{id}")
    @ApiOperation("逻辑删除医院设置信息")
    public Result removeHospitalSet(@PathVariable Long id) {
        return hospitalSetService.removeById(id) ? Result.ok() : Result.fail();
    }

    @PostMapping("findPage/{current}/{limit}")
    @ApiOperation("分页查询医院设置信息")
    public Result<Page<HospitalSet>> findPageHospSet(@PathVariable long current, @PathVariable long limit,
                                                     @RequestBody(required = false) HospitalSetQueryVo hospitalSetQueryVo) {
        Page<HospitalSet> page = new Page<>(current, limit);
        QueryWrapper<HospitalSet> wrapper = new QueryWrapper<>();
        String hoscode = hospitalSetQueryVo.getHoscode();
        String hosname = hospitalSetQueryVo.getHosname();
        if (StrUtil.isNotEmpty(hosname)) {
            wrapper.like("hosname", hosname);
        }
        if (StrUtil.isNotEmpty(hoscode)) {
            wrapper.eq("hoscode", hoscode);
        }
        return Result.ok(hospitalSetService.page(page, wrapper));
    }

    @PostMapping("saveHospitalSet")
    public Result saveHospitalSet(@RequestBody HospitalSet hospitalSet) {
        hospitalSet.setStatus(1);
        Random random = new Random();
        hospitalSet.setSignKey(DigestUtil.md5Hex(System.currentTimeMillis() + "" + random.nextInt(1000)));
        boolean save = hospitalSetService.save(hospitalSet);
        if (save){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }

    @GetMapping("/getHospitalSet/{id}")
    public Result getHospitalSet(@PathVariable Long id){
        return Result.ok(hospitalSetService.getById(id));
    }

    @PostMapping("/updateHospitalSet")
    public Result updateHospitalSet(@RequestBody HospitalSet hospitalSet){
        return Result.ok(hospitalSetService.updateById(hospitalSet));
    }

    @DeleteMapping("/batchRemove")
    public Result batchRemoveHospitalSet(@RequestBody List<Long> idList){
        return Result.ok(hospitalSetService.removeByIds(idList));
    }

    @PutMapping("lockHospitalSet/{id}/{status}")
    public Result lockHospitalSet(@PathVariable Long id,@PathVariable Integer status){
        HospitalSet hospitalSet = hospitalSetService.getById(id);
        hospitalSet.setStatus(status);
        hospitalSetService.updateById(hospitalSet);
        return Result.ok();
    }

    @PutMapping("sendKey/{id}")
    public Result sendKey(@PathVariable Long id){
        HospitalSet hospitalSet = hospitalSetService.getById(id);
        String signKey = hospitalSet.getSignKey();
        String hoscode = hospitalSet.getHoscode();
        //todo 发送短信
        return Result.ok();
    }

    public static void main(String[] args) {
        Random random = new Random();
        String key  = DigestUtil.md5Hex(System.currentTimeMillis() + "" + random.nextInt(1000));
        System.out.println(key);
    }
}
