package com.universe.toastmasters.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.universe.toastmasters.pojo.domain.GrammarianReportDetailDO;

import java.util.List;

/**
 * <p>
 * 语法官汇报详情表 Mapper 接口
 * </p>
 *
 * @author Nick Liu
 * @since 2024-06-05
 */
public interface GrammarianReportDetailMapper extends BaseMapper<GrammarianReportDetailDO> {

	void batchSaveReportDetail(List<GrammarianReportDetailDO> reportDetailDOList);
}
