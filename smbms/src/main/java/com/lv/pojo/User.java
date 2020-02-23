package com.lv.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private Integer id; // id
	private String userCode; // 用户编码
	private String userName; // 用户名称
	private String userPassword; // 用户密码
	private Integer gender; // 性别
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthday; // 出生日期
	private String phone; // 电话
	private String address; // 地址
	private Integer userRole; // 用户角色
	private Integer createdBy; // 创建者
	private Date creationDate; // 创建时间
	private Integer modifyBy; // 更新者
	private Date modifyDate; // 更新时间
	private Integer age;// 年龄
	private String userRoleName; // 用户角色名称

	private String idPicPath; // 证件照路径

	private String workPicPath; // 工作证照片路径


	public User(Integer id, String userCode, String userName, String userPassword, Integer gender, Date birthday,
			String phone, String address, Integer userRole, Integer createdBy, Date creationDate, Integer modifyBy,
			Date modifyDate) {
		this.id = id;
		this.userCode = userCode;
		this.userName = userName;
		this.userPassword = userPassword;
		this.gender = gender;
		this.birthday = birthday;
		this.phone = phone;
		this.address = address;
		this.userRole = userRole;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.modifyBy = modifyBy;
		this.modifyDate = modifyDate;
	}

	public Integer getAge() {
		/*
		 * long time = System.currentTimeMillis()-birthday.getTime(); Integer age =
		 * Long.valueOf(time/365/24/60/60/1000).IntegerValue();
		 */
		Date date = new Date();
		Integer age = date.getYear() - birthday.getYear();
		return age;
	}

}
