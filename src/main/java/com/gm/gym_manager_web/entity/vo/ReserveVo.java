package com.gm.gym_manager_web.entity.vo;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Date;

/**
 * @author 陈桢梁
 * @desc ReserveVo.java
 * @date 2023-05-09 22:12
 * @logs[0] 2023-05-09 22:12 陈桢梁 创建了ReserveVo.java文件
 */
@Data
public class ReserveVo {

    private Integer id;

    @NotNull(message = "数量不能为空")
    private Integer number;

    @NotNull(message = "商品id不能为空")
    @JsonAlias({"goods_id"})
    private Integer goodsId;

    @NotNull(message = "商品id不能为空")
    @JsonAlias({"gym_id"})
    private Integer gymId;

    @NotNull(message = "日期不能为空")
    private Date today;

    @Max(value = 24, message = "最大值不能超过23")
    @Min(value = 0, message = "最小值不能小于0")
    private int start;

    @Max(value = 24, message = "最大值不能超过23")
    @Min(value = 0, message = "最小值不能小于0")
    private int end;
}
