package com.cll.yygh.cmn.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cll.yygh.cmn.config.DictListener;
import com.cll.yygh.cmn.service.DictService;
import com.cll.yygh.cmn.mapper.DictMapper;
import com.cll.yygh.entity.cmn.Dict;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 *
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    @Override
    @Cacheable(value = "dictCache")
    public List<Dict> findChildData(Long id) {
        List<Dict> list = super.list(new LambdaQueryWrapper<Dict>().eq(Dict::getParentId, id));

        for (Dict dict : list) {
            dict.setHasChildren(hasChildren(dict.getId()));
        }
        return list;
    }

    @Override
    public void exportDict(HttpServletResponse response) throws IOException {
        //设置请求头，这里生成完成要下载
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("UTF-8");
        String fileName= URLEncoder.encode("数据字段","UTF-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + fileName+".xlsx");
        List<Dict> list = super.list();
        EasyExcel.write(response.getOutputStream(),Dict.class)
        .sheet("dict")
        .doWrite(list);
    }

    @Override
    @CacheEvict(value = "dictCache",allEntries = true)
    public void importDict(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(),Dict.class,new DictListener(this))
                .sheet().doRead();
    }

    private boolean hasChildren(Long id) {
        LambdaQueryWrapper<Dict> wrapper = new LambdaQueryWrapper<Dict>().eq(Dict::getParentId, id);
        return super.count(wrapper) > 0;
    }
}




