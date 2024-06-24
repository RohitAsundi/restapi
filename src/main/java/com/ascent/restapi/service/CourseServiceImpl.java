package com.ascent.restapi.service;

import com.ascent.restapi.entity.Course;
import com.ascent.restapi.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepo;

    @Override
    public String upsert(Course course) {
        courseRepo.save(course);
        return "Saved";
    }

    @Override
    public Course getById(Integer id) {
        Optional<Course> findById = courseRepo.findById(id);
        return findById.orElse(null); // Return the Course if present, otherwise null
    }

    @Override
    public List<Course> getAllCourse(){
        return courseRepo.findAll();
    }

    @Override
    public String deleteById(Integer id) {
        if(courseRepo.existsById(id)){
            courseRepo.deleteById(id);
            return "Delete Sucess";
        }
        return "Id not Found";
    }

}
