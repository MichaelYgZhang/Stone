package com.stone.service;

import com.stone.SpringbootexampleApplicationTests;
import com.stone.bean.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserInfoServiceTest extends SpringbootexampleApplicationTests {

	@Autowired
	private UserInfoService userInfoService;

	@Test
	public void testFindAll() {
		final List<UserInfo> userInfoList = userInfoService.findAll();
		assert userInfoList.isEmpty() == false;
	}

	@Test
	public void testDeleteById() {
		final Boolean flag = userInfoService.deleteById(1L);
		assert flag == false;
	}

}
