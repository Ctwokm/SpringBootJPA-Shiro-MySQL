package com.ctwokm.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.ctwokm.pojo.User;
import com.ctwokm.repository.UserRepository;

/**
 * @author Ctwokm
 *
 *	1、这是一个User业务类，提供对User类的一些基本的业务需求操作
 *
 *	2、注入之后基本的增、删、改、查已经存在
 *
 *	3、根据用户名获取密码以及根据用户名获取用户全部信息等
 */
public class UserService {
	
	//注入repository
	@Autowired
	UserRepository userRepository;
	
	//获取密码用来完成登陆验证
	public String getPasswordByUsername(String username) {
		User user = userRepository.findByusername(username);
		//考虑到用户不存在的情况
		if (user == null) {
			return null;
		}
		return user.getPassword();
	}
	
	//查询当前用户的VIP等级
	public int getLevelByUsername(String username) {
		User user = userRepository.findByusername(username);
		if (user == null) {
			return 0;
		}
		return user.getLevel().getRoleLevel();
	}
	
	//用户注册，这里需要特别处理，需要将密码以及其他重要信息进行加密处理
	
	
}
