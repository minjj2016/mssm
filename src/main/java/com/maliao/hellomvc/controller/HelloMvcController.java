package com.maliao.hellomvc.controller;

import com.maliao.hellomvc.dao.impl.StudentDao;
import com.maliao.hellomvc.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/hello")
public class HelloMvcController {

	private StudentDao studentDao;
	@Autowired
	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}

	@RequestMapping("/mvc")
	public String helloMvc() {
		

		return "springmvc/hellomvc";
	}


	@RequestMapping("/json")
	@ResponseBody
	public Map<String,Object> helloJson(@RequestParam(value = "k")String k) {
		Map map = new HashMap<>();

		String a = "哈哈哈哈";
		System.out.println(a);
		map.put("a",a);
		return map;
	}

	/* 查询 */
	@RequestMapping("/find")
	@ResponseBody
	public User testFindUserByNameJdbc() throws Exception {
		System.out.println("哈哈哈哈");
		User user = studentDao.findUserByNameJdbc("jack");

		System.out.println(user.toString());

		return user;
	}

}
