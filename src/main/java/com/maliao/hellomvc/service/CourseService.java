package com.maliao.hellomvc.service;

import com.maliao.hellomvc.entity.Course;
import org.springframework.stereotype.Service;


@Service
public interface CourseService {
	
	
	Course getCoursebyId(Integer courseId);
	

	
	

}
