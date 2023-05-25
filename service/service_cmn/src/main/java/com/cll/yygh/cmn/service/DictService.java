package com.cll.yygh.cmn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cll.yygh.entity.cmn.Dict;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *
 */
public interface DictService extends IService<Dict> {

    List<Dict> findChildData(Long id);

    void exportDict(HttpServletResponse response) throws IOException;

    void importDict(MultipartFile file) throws IOException;
}
