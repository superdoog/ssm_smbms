package com.lv.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {

	private Integer id; // id
	private String roleCode; // 角色编码
	private String roleName; // 角色名称
	private Integer createdBy; // 创建者
	@JSONField(format="yyyy-MM-dd")
	private Date creationDate; // 创建时间
	private Integer modifyBy; // 更新者
	@JSONField(format="yyyy-MM-dd")
	private Date modifyDate;// 更新时间

}
