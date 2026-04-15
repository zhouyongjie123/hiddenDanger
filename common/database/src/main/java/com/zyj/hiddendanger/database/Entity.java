package com.zyj.hiddendanger.database;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.*;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public abstract class Entity extends SuperEntity {
    @TableField(fill = FieldFill.INSERT)
    protected String creatorId;

    @TableField(fill = FieldFill.UPDATE)
    protected String updaterId;

    @Override
    public String toString() {
        return super.toString();
    }
}
