package com.generator.mapper;

import com.generator.model.DictVehPlan;
import com.generator.model.DictVehPlanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DictVehPlanMapper {
    long countByExample(DictVehPlanExample example);

    int deleteByExample(DictVehPlanExample example);

    int insert(DictVehPlan record);

    int insertSelective(DictVehPlan record);

    List<DictVehPlan> selectByExample(DictVehPlanExample example);

    int updateByExampleSelective(@Param("record") DictVehPlan record, @Param("example") DictVehPlanExample example);

    int updateByExample(@Param("record") DictVehPlan record, @Param("example") DictVehPlanExample example);
}