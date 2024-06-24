package com.ascent.restapi.service;

import com.ascent.restapi.entity.Course;
import java.util.List;

public interface CourseService {

    public String upsert(Course course);

    public Course getById(Integer id);

    public List<Course> getAllCourse();

    public String deleteById(Integer id);

}
