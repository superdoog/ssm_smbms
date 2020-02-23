package com.lv.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * 订单实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bill {
	/**
	 * 主键ID
	 */
	private Integer id;

	/**
	 * 账单编码
	 */
	private String billCode;

	/**
	 * 商品名称
	 */
	private String productName;

	/**
	 * 商品描述
	 */
	private String productDesc;

	/**
	 * 商品单位
	 */
	private String productUnit;

	/**
	 * 商品数量
	 */
	private Integer productCount;

	/**
	 * 商品总额
	 */
	private Integer totalPrice;

	/**
	 * 是否支付（1：未支付 2：已支付）
	 */
	private Integer isPayment;

	/**
	 * 创建者（userId）
	 */
	private Integer createdBy;

	/**
	 * 创建时间
	 */
	private Date creationDate;

	/**
	 * 更新者（userId）
	 */
	private Integer modifyBy;

	/**
	 * 更新时间
	 */
	private Date modifyDate;

	/**
	 * 供应商ID
	 */
	private Integer providerId;

	private List<Provider> providers;
	
	private String proName;

}
