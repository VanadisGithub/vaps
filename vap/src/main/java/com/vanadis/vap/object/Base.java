package com.vanadis.vap.object;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @description:
 * @author: Created by 遥远 on 2019-05-10 00:25
 */
@Data
@MappedSuperclass //作为基类，本身不映射
public class Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false) //上面三个标签：为了 Hibernate 自增主键
    @TableId(type = IdType.AUTO)
    private Long id;

    @TableLogic(value = "0", delval = "1")
    private boolean isDeleted;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
