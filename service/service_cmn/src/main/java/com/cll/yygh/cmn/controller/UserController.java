package com.cll.yygh.cmn.controller;

import com.cll.yygh.common.result.Result;
import com.cll.yygh.common.result.ResultCodeEnum;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@Api("用户")
@RequestMapping("/user")
public class UserController {
    @PostMapping("/login")
    public Result login(@RequestBody Map<String,String>  userInfo){
        Result<Map<String,Object>> result = new Result<>();
        HashMap<String, Object> map = new HashMap<>();
        map.put("token","admin");
        result.setData(map);
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        return result;
    }

    @GetMapping("/info")
    public Result info(@RequestParam String token){
        Result<Map<String,Object>> result = new Result<>();
        HashMap<String, Object> map = new HashMap<>();
        map.put("roles", Arrays.asList("admin"));
        map.put("name","admin");

        result.setData(map);
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        return result;
    }
}
