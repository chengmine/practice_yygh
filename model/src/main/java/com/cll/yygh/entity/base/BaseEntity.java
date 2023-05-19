package com.cll.yygh.entity.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.util.Date;

/**
 * @Author chengll
 * @Date 2023/4/9 21:57
 * @Desc BaseEntity
 */
@Data
public class BaseEntity {
    //编号
    @TableId(type = IdType.AUTO)
    private Long id;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //逻辑删除(1:已删除，0:未删除)
    @TableLogic
    private Integer isDeleted;
}
