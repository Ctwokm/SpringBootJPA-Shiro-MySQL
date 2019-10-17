package com.ctwokm.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Ctwokm
 *
 *         1、将权限分为基本权限、附加权限、顶级权限
 * 
 *         3、权限是会员等级的附属品，一旦失去相应等级会员就会失去对应权限
 */

@Entity
@Table(name = "permission")
public class Permission {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	// 权限
	@Column(name = "permission")
	private String permission;

	// 权限地址（网址）
	@Column(name = "permissionUrl")
	private String permissionUrl;

	// 方法
	@Column(name = "method")
	private String method;

	// 描述
	@Column(name = "description")
	private String description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getPermissionUrl() {
		return permissionUrl;
	}

	public void setPermissionUrl(String permissionUrl) {
		this.permissionUrl = permissionUrl;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
