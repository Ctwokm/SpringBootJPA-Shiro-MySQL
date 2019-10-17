package com.ctwokm.pojo;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author Ctwokm
 *
 *         会员等级分为四个级别：大众会员、黄金会员、豪华会员
 *
 *         1、大众会员（ordinary）：普通会员为注册基本会员，所有人都享受拥有对应权限
 *
 *         2、黄金会员（gold）：10元每月包月服务会员，拥有相对于大众会员更多的功能访问权限
 *
 *         3、豪华会员（luxury）：18元每月包月服务会员，拥有所有优质服务和所以功能访问权限
 */

@Entity
@Table(name = "membershipLevel")
public class MembershipLevel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	// 会员名称，默认值为ordinary
	@Column(name = "status")
	private String status;

	// 会员等级
	@Column(name = "roleLevel")
	private int roleLevel;

	// 会员描述
	@Column(name = "description")
	private String description;

	// 会员结束时间
	@Column(name = "endTime")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endTime;

	/*
	 * 设置会员等级表与权限表的多对多关系 多对多关系需要设置中间表
	 */

	@ManyToMany(cascade = {}, fetch = FetchType.EAGER)
	@JoinTable(name = "membershipLevel_permission", joinColumns = { @JoinColumn(name = "membershipLevel_id") }, inverseJoinColumns = {
			@JoinColumn(name = "permission_id") })
	private List<Permission> permissions;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public int getRoleLevel() {
		return roleLevel;
	}

	public void setRoleLevel(int roleLevel) {
		this.roleLevel = roleLevel;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

}
