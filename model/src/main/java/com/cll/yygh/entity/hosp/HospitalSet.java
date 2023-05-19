package com.cll.yygh.entity.hosp;

import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.cll.yygh.entity.base.BaseEntity;
import com.sun.xml.internal.rngom.parse.host.Base;
import lombok.Data;

/**
 * 医院设置表(HospitalSet)表实体类
 *
 * @author makejava
 * @since 2023-04-09 21:54:45
 */
@Data
public class HospitalSet extends BaseEntity {
    //医院名称
    private String hosname;
    //医院编号
    private String hoscode;
    //api基础路径
    private String apiUrl;
    //签名秘钥
    private String signKey;
    //联系人
    private String contactsName;
    //联系人手机
    private String contactsPhone;

    private Integer status;
}
