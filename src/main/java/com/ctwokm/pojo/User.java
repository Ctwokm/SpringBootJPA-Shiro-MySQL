package com.ctwokm.pojo;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

/*
 * @Entity和@Table(name="")注解：表明这是一个实体类。一般用于jpa这两个注解一般一块使用，但是如果表名和实体类名相同的话，@Table注解可以省略。
 */
/**
 * @author Ctwokm
 * 
 *         1、一个用户只有一份详细信息，所以用户表与详细信息表是一对一关系，且详细信息表只与用户表有关联
 * 
 *         2、一个用户只有一个会员等级，所以用户表与会员表是一对一关系
 *
 *         3、一个会员等级拥有多个权限，且每个权限可以属于不同的会员等级，因此会员等级表与权限表是多对多关系
 */
@Entity
@Table(name = "user")
public class User {
	/*
	 * @GeneratedValue(strategy = GenerationType.SEQUENCE,generator =
	 * "repair_seq")：
	 * 表示主键生成策略是sequence（可以为Auto、IDENTITY、native等，Auto表示可在多个数据库间切换），
	 * 指定sequence的名字是repair_seq。
	 * 
	 * 1、AUTO 自动选择一个最适合底层数据库的主键生成策略。如MySQL会自动对应auto increment。
	 * 这个是默认选项，即如果只写@GeneratedValue，等价于@GeneratedValue(strategy=
	 * GenerationType.AUTO)。
	 *
	 * 2、IDENTITY 表自增长字段，Oracle不支持这种方式。
	 *
	 * 3、SEQUENCE 通过序列产生主键，MySQL不支持这种方式。
	 *
	 * 4、TABLE 通过表产生主键，框架借由表模拟序列产生主键，使用该策略可以使应用更易于数据库移植。 不同的JPA实现商生成的表名是不同的，如
	 * OpenJPA生成openjpa_sequence_table表，
	 * Hibernate生成一个hibernate_sequences表，而TopLink则生成sequence表。
	 * 这些表都具有一个序列名和对应值两个字段，如SEQ_NAME和SEQ_COUNT。
	 * 在我们的应用中，一般选用@GeneratedValue(strategy=GenerationType.AUTO)这种方式，自动选择主键生成策略，
	 * 以适应不同的数据库移植。
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	/*
	 * @Column
	 * 
	 * （1）name属性：被标注字段在数据库表中所对应字段的名称；
	 * 
	 * （2）length属性：表示该字段的长度，当字段的类型为varchar时，该属性才有效果，默认为255个字符；
	 * 
	 * （3）nullable属性：表示该字段是否可以为null值，默认是true。
	 * 
	 * （4）unique属性：表示该字段是否为唯一标识，默认fasle。
	 * 
	 * （5）precision和scale属性：precision属性和scale属性表示精度，当字段类型为double时，
	 * 
	 * precision表示数值的总长度，scale表示小数点所占的位数。
	 */
	// 用户名
	@Column(name = "username", unique = true)
	private String username;

	// 密码
	@Column(name = "password")
	private String password;

	// 盐
	@Column(name = "salt")
	private String salt;

	// 创建日期
	// @DateTimeFormat日期格式化
	@Column(name = "createDate")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createDate;

	// 设置用户表与详情表的一对一关系
	// CascadeType.REMOVE再删除用户信息时，用户详情也会同时删除
	@OneToOne(cascade = CascadeType.REMOVE)
	// 默认关联字段名字为属性名_id,这里可以不用手动定义
	// @JoinColumn(name = "details_id")
	private UserDetails details;

	// 设置用户表和会员等级表的一对一关系
	@OneToOne
	// 自动生成默认关联字段，无需维护，删除本表的用户信息，不会对会员等级表的信息产生影响
	private MembershipLevel level;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public UserDetails getDetails() {
		return details;
	}

	public void setDetails(UserDetails details) {
		this.details = details;
	}

	public MembershipLevel getLevel() {
		return level;
	}

	public void setLevel(MembershipLevel level) {
		this.level = level;
	}

}
