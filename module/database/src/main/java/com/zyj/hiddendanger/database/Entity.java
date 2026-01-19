package com.zyj.hiddendanger.database;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.*;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public abstract class Entity extends SuperEntity {
    @TableField(fill = FieldFill.INSERT)
    protected Long creatorId;

    @TableField(fill = FieldFill.UPDATE)
    protected Long updaterId;
}
