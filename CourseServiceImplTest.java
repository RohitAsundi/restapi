package com.ascent.restapi.service;

import com.ascent.restapi.entity.Course;
import com.ascent.restapi.repository.CourseRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CourseServiceImplTest {

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseServiceImpl courseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Test upsert method")
    void testUpsert() {
        // Given
        Course course = new Course(1, "Java Basics", 1000);

        // When
        String result = courseService.upsert(course);

        // Then
        Assertions.assertEquals("Saved", result);
        Mockito.verify(courseRepository, Mockito.times(1)).save(course);
    }

    @Test
    @DisplayName("Test getById method")
    void testGetById() {
        // Given
        Course course = new Course(1, "Java Basics", 1000);
        Mockito.when(courseRepository.findById(1)).thenReturn(Optional.of(course));

        // When
        Course result = courseService.getById(1);

        // Then
        Assertions.assertEquals(course, result);
        Mockito.verify(courseRepository, Mockito.times(1)).findById(1);
    }

    @Test
    @DisplayName("Test getById method with non-existent id")
    void testGetByIdNotFound() {
        // Given
        Mockito.when(courseRepository.findById(2)).thenReturn(Optional.empty());

        // When
        Course result = courseService.getById(2);

        // Then
        Assertions.assertNull(result);
        Mockito.verify(courseRepository, Mockito.times(1)).findById(2);
    }

    @Test
    @DisplayName("Test getAllCourse method")
    void testGetAllCourse() {
        // Given
        List<Course> courses = Arrays.asList(
                new Course(1, "Java Basics", 1000),
                new Course(2, "Spring Boot", 2000)
        );
        Mockito.when(courseRepository.findAll()).thenReturn(courses);

        // When
        List<Course> result = courseService.getAllCourse();

        // Then
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(courses, result);
        Mockito.verify(courseRepository, Mockito.times(1)).findAll();
    }

    @Test
    @DisplayName("Test deleteById method")
    void testDeleteById() {
        // Given
        Integer courseId = 1;
        Mockito.when(courseRepository.existsById(courseId)).thenReturn(true);

        // When
        String result = courseService.deleteById(courseId);

        // Then
        Assertions.assertEquals("Delete Success", result);
        Mockito.verify(courseRepository, Mockito.times(1)).deleteById(courseId);
    }

    @Test
    @DisplayName("Test deleteById method with non-existent id")
    void testDeleteByIdNotFound() {
        // Given
        Integer courseId = 2;
        Mockito.when(courseRepository.existsById(courseId)).thenReturn(false);

        // When
        String result = courseService.deleteById(courseId);

        // Then
        Assertions.assertEquals("Id not Found", result);
        Mockito.verify(courseRepository, Mockito.never()).deleteById(courseId);
    }
}