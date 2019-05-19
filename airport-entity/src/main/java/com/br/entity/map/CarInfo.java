package com.br.entity.map;

import lombok.*;

import java.math.BigDecimal;

/**
 * 车辆信息 实例
 *
 * @Author Zero
 * @Date 2019 03 25
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CarInfo {
    @Getter
    @Setter
    Integer carInfoSeq;
    @Getter
    @Setter
    Integer carSeq;
    @Getter
    @Setter
    BigDecimal carLongitude;
    @Getter
    @Setter
    BigDecimal carLatitude;
}
