package Lesson9;

import java.util.List;
import java.util.stream.Collectors;

public class HighSchool {

    List<Course> getAllCoursesAllStudents(List<Student> students) {
        List<Course> allCourses = students.stream()
                .map(it -> it.getAllCourses())
                .flatMap(Course -> Course.stream())
                .distinct()
                .collect(Collectors.toList());
        return allCourses;
    }

    List<Student> getCleverStudents(List<Student> students) {
        List<Student> cleverStudents = students.stream()
                .filter(it -> (it.getAllCourses().size() >= 3))
                .collect(Collectors.toList());
        return cleverStudents;
    }

    List<Student> getStudentsForCourse(List<Student> students, Course course) {
        List<Student> studentList = students.stream()
                .filter(it -> it.getAllCourses().contains(course))
                .collect(Collectors.toList());
        return studentList;
    }
}
