package com.universe.toastmasters.pojo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Nick Liu
 * @date 2022/8/28
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AhCounterReportModel {

	/**
	 * 汇报人
	 */
	private String ahCounter;

	/**
	 * 列表
	 */
	private List<AhCounterDataModel> dataModelList;

}
