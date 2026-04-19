package com.zyj.hiddendanger.database;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Getter
@Setter
@ToString(of = {"id"})
public abstract class SuperEntity implements Serializable {
    @TableId
    protected String id;

    @TableLogic
    @TableField(value = "is_deleted",
            fill = FieldFill.INSERT)
    protected Integer deleted;

    @TableField(fill = FieldFill.INSERT)
    protected Date createTime;

    @TableField(fill = FieldFill.UPDATE)
    protected Date updateTime;

    @Serial
    private static final long serialVersionUID = 1L;
}
