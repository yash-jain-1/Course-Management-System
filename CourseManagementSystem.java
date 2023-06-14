package com.example.cms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

class Course {
    private int courseId;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private Instructor instructor;
    private List<Student> enrolledStudents;

    public Course(int courseId, String name, String description, Date startDate, Date endDate, Instructor instructor) {
        this.courseId = courseId;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.instructor = instructor;
        this.enrolledStudents = new ArrayList<>();
    }

    public int getCourseId() {
        return courseId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void enrollStudent(Student student) {
        enrolledStudents.add(student);
    }

    public void dropStudent(Student student) {
        enrolledStudents.remove(student);
    }
}

class Student {
    private int studentId;
    private String name;
    private String email;

    public Student(int studentId, String name, String email) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}

class Instructor {
    private int instructorId;
    private String name;
    private String email;

    public Instructor(int instructorId, String name, String email) {
        this.instructorId = instructorId;
        this.name = name;
        this.email = email;
    }

    public int getInstructorId() {
        return instructorId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}

public class CourseManagementSystem {
    private List<Course> courses;
    private List<Student> students;
    private List<Instructor> instructors;

    public CourseManagementSystem() {
        this.courses = new ArrayList<>();
        this.students = new ArrayList<>();
        this.instructors = new ArrayList<>();
    }

    public void createCourse(int courseId, String name, String description, Date startDate, Date endDate, Instructor instructor) {
        Course course = new Course(courseId, name, description, startDate, endDate, instructor);
        courses.add(course);
    }

    public Course getCourseById(int courseId) {
        for (Course course : courses) {
            if (course.getCourseId() == courseId) {
                return course;
            }
        }
        return null;
    }

    public void enrollStudent(int studentId, int courseId) {
        Student student = getStudentById(studentId);
        Course course = getCourseById(courseId);
        if (student != null && course != null) {
            course.enrollStudent(student);
        }
    }

    public List<Course> getAllCourses() {
        return courses;
}

    public void dropStudent(int studentId, int courseId) {
        Student student = getStudentById(studentId);
        Course course = getCourseById(courseId);
        if (student != null && course != null) {
            course.dropStudent(student);
        }
    }

    public void addStudent(int studentId, String name, String email) {
        Student student = new Student(studentId, name, email);
        students.add(student);
    }

    public Student getStudentById(int studentId) {
        for (Student student : students) {
            if (student.getStudentId() == studentId) {
                return student;
            }
        }
        return null;
    }

    public void addInstructor(int instructorId, String name, String email) {
        Instructor instructor = new Instructor(instructorId, name, email);
        instructors.add(instructor);
    }

    public Instructor getInstructorById(int instructorId) {
        for (Instructor instructor : instructors) {
            if (instructor.getInstructorId() == instructorId) {
                return instructor;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        CourseManagementSystem system = new CourseManagementSystem();

        // Create instructors
        Instructor instructor1 = new Instructor(1, "John Doe", "john.doe@example.com");
        Instructor instructor2 = new Instructor(2, "Jane Smith", "jane.smith@example.com");

        // Add instructors to the system
        system.addInstructor(instructor1.getInstructorId(), instructor1.getName(), instructor1.getEmail());
        system.addInstructor(instructor2.getInstructorId(), instructor2.getName(), instructor2.getEmail());

        // Create courses
        system.createCourse(101, "Java Programming", "Learn Java programming basics", new Date(), new Date(), instructor1);
        system.createCourse(102, "Database Management", "Introduction to database management", new Date(), new Date(), instructor2);

        // Add students
        system.addStudent(1, "Alice Brown", "alice.brown@example.com");
        system.addStudent(2, "Bob Green", "bob.green@example.com");

        // Enroll students in courses
        system.enrollStudent(1, 101);
        system.enrollStudent(2, 101);

        // Drop student from a course
        system.dropStudent(1, 101);

        System.out.println(system.getStudentById(1));
        System.out.println("--------------- Course Management System --------------");

        System.out.println("1.Add Student");
        System.out.println("2.Enroll Student");
        System.out.println("3.Drop Student");
        System.out.println("4.Create Curse");
        System.out.println("5.Add Instructor");
        System.out.println("If you want to exit input 'q'");

        System.out.println("What do you want to do? ->");
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        while(input!='q'){
            if (input.equals('1')){
                System.out.println("Enter Name:");
                String name = sc.next();
                System.out.println("Enter Student ID:");
                int sid = sc.nextInt();
                System.out.println("Enter email:");
                String email = sc.next();
                system.addStudent(sid, name, email);

            } else if (input.equals('2')) {
                System.out.println("Enter student Id ");
                int sid = sc.nextInt();
                System.out.println("Enter Course Id ");
                int cid = sc.nextInt();
                system.enrollStudent(sid, cid);

            } else if (input.equals('3')) {
                System.out.println("Enter Student ID");
                int sid = sc.nextInt();
                System.out.println("Enter Course ID");
                int cid = sc.nextInt();
                system.dropStudent(sid, cid);
            } else if (input.equals('4')) {
                System.out.println("Enter course ID:");
                int cid = sc.nextInt();
                System.out.println("Enter Name:");
                String name = sc.next();
                System.out.println("Enter Description:");
                String descp = sc.next();

                system.createCourse(cid, name, descp, new Date(), new Date(), instructor2);
            } else if (input.equals('5')) {
                System.out.println("Enter Name:");
                String name = sc.next();
                System.out.println("Enter Instructor ID:");
                int iid = sc.nextInt();
                System.out.println("Enter Email:");
                String email = sc.next();
                Instructor instruct = new Instructor(iid,name, email);
            }
        }

    }


}