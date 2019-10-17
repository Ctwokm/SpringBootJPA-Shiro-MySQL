package com.ctwokm.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Ctwokm
 *
 *         1、用户详细信息表，主要用来存放每个用户的详细信息
 *
 *         2、每个用户拥有一份详细信息，所以用户和详细信息表为一对一关系
 *
 *         3、详细信息主要包括用户出生日期、出生地、性别、身份证号码、手机号等信息
 *
 */
@Entity
@Table(name = "userDetails")
public class UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	// 中文名
	@Column(name = "cnName")
	private String cnName;

	// 邮箱号
	@Column(name = "email")
	private String email;

	// 手机号
	@Column(name = "phoneNumber")
	private long phoneNumber;

	// 微信号
	@Column(name = "wechatId")
	private String wechatId;

	// 出生日期
	@Column(name = "birthday")
	private Date birthday;

	// 性别
	@Column(name = "sex")
	private boolean sex;

	// 地址
	@Column(name = "address")
	private String address;

	// 身份证号码
	@Column(name = "IDCardNumber")
	private long IDCardNumber;

	// 拥有技能特长
	@Column(name = "skill")
	private String skill;

	// 登陆次数
	@Column(name = "loginCount")
	private Integer loginCount;

	// 描述
	@Column(name = "description")
	private String description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCnName() {
		return cnName;
	}

	public void setCnName(String cnName) {
		this.cnName = cnName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getWechatId() {
		return wechatId;
	}

	public void setWechatId(String wechatId) {
		this.wechatId = wechatId;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public boolean isSex() {
		return sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getIDCardNumber() {
		return IDCardNumber;
	}

	public void setIDCardNumber(long iDCardNumber) {
		IDCardNumber = iDCardNumber;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public Integer getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
