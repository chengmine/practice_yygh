package com.cll.yygh.entity.cmn;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 组织架构表
 *
 * @TableName dict
 */
@Data
public class Dict implements Serializable {
    /**
     * id
     */
    @TableId
    @ExcelProperty(value = "id",index = 0)
    private Long id;

    /**
     * 上级id
     */
    @ExcelProperty(value = "上级id",index = 1)
    private Long parentId;

    /**
     * 名称
     */
    @ExcelProperty(value = "名称",index = 2)
    private String name;

    /**
     * 值
     */
    @ExcelProperty(value = "值",index = 3)
    private Long value;

    /**
     * 编码
     */
    @ExcelProperty(value = "编码",index = 4)
    private String dictCode;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间",index = 5)
    private Date createTime;

    /**
     * 更新时间
     */
    @ExcelProperty(value = "更新时间",index = 6)
    private Date updateTime;

    /**
     * 删除标记（0:不可用 1:可用）
     */
    @ExcelProperty(value = "删除标记",index = 7)
    private Integer isDeleted;

    @TableField(exist = false)
    private boolean hasChildren;

    private static final long serialVersionUID = 1L;


}