package com.br.mapper;

import com.br.entity.user.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * 部门 Mapper
 *
 * @Author zyx
 * @Date 2019 03 01
 */
@Mapper
public interface DepartmentMapper {

    /**
     * 单个部门查询 By 序号
     * @return
     */
    Department find(@Param("deptSeq") Integer deptSeq);

}