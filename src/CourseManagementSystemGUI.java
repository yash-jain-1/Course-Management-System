import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Date;
import java.util.List;

import javax.swing.text.html.ListView;

public class CourseManagementSystemGUI extends Application {
    private CourseManagementSystem system;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        system = new CourseManagementSystem();

        primaryStage.setTitle("Course Management System");

        TabPane tabPane = new TabPane();
        tabPane.getTabs().addAll(createCourseTab(), enrollStudentTab());

        VBox vbox = new VBox(tabPane);
        vbox.setPadding(new Insets(10));

        Scene scene = new Scene(vbox, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Tab createCourseTab() {
        Tab tab = new Tab("Create Course");

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));

        Label courseIdLabel = new Label("Course ID:");
        TextField courseIdTextField = new TextField();

        Label courseNameLabel = new Label("Course Name:");
        TextField courseNameTextField = new TextField();

        Label courseDescLabel = new Label("Description:");
        TextArea courseDescTextArea = new TextArea();

        Label startDateLabel = new Label("Start Date:");
        DatePicker startDatePicker = new DatePicker();

        Label endDateLabel = new Label("End Date:");
        DatePicker endDatePicker = new DatePicker();

        Label instructorLabel = new Label("Instructor:");
        ObservableList<Instructor> instructors = FXCollections.observableArrayList(
                new Instructor(1, "John Doe", "john.doe@example.com"),
                new Instructor(2, "Jane Smith", "jane.smith@example.com")
        );
        ComboBox<Instructor> instructorComboBox = new ComboBox<>(instructors);

        Button createCourseBtn = new Button("Create Course");
        createCourseBtn.setOnAction(e -> {
            int courseId = Integer.parseInt(courseIdTextField.getText());
            String courseName = courseNameTextField.getText();
            String courseDesc = courseDescTextArea.getText();
            Date startDate = java.sql.Date.valueOf(startDatePicker.getValue());
            Date endDate = java.sql.Date.valueOf(endDatePicker.getValue());
            Instructor instructor = instructorComboBox.getValue();

            system.createCourse(courseId, courseName, courseDesc, startDate, endDate, instructor);
            showAlert(Alert.AlertType.INFORMATION, "Course created successfully!");
        });

        gridPane.add(courseIdLabel, 0, 0);
        gridPane.add(courseIdTextField, 1, 0);
        gridPane.add(courseNameLabel, 0, 1);
        gridPane.add(courseNameTextField, 1, 1);
        gridPane.add(courseDescLabel, 0, 2);
        gridPane.add(courseDescTextArea, 1, 2);
        gridPane.add(startDateLabel, 0, 3);
        gridPane.add(startDatePicker, 1, 3);
        gridPane.add(endDateLabel, 0, 4);
        gridPane.add(endDatePicker, 1, 4);
        gridPane.add(instructorLabel, 0, 5);
        gridPane.add(instructorComboBox, 1, 5);
        gridPane.add(createCourseBtn, 1, 6);

        tab.setContent(gridPane);
        return tab;
    }

    private Tab enrolledStudentsTab() {
        Tab tab = new Tab("Enrolled Students");

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));

        Label courseIdLabel = new Label("Course ID:");
        TextField courseIdTextField = new TextField();

        Button viewStudentsBtn = new Button("View Enrolled Students");
        viewStudentsBtn.setOnAction(e -> {
            int courseId = Integer.parseInt(courseIdTextField.getText());
            Course course = system.getCourseById(courseId);

            if (course != null) {
                List<Student> enrolledStudents = course.getEnrolledStudents();

                ListView<Student> studentListView = new ListView<>();
                ObservableList<Student> studentItems = FXCollections.observableArrayList(enrolledStudents);
                studentListView.setItems(studentItems);

                gridPane.add(studentListView, 0, 1, 2, 1);
            } else {
                showAlert(Alert.AlertType.ERROR, "Course not found!");
            }
        });

        gridPane.add(courseIdLabel, 0, 0);
        gridPane.add(courseIdTextField, 1, 0);
        gridPane.add(viewStudentsBtn, 1, 2);

        tab.setContent(gridPane);
        return tab;
    }

    
    private Tab enrollStudentTab() {
        Tab tab = new Tab("Enroll Student");

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));

        Label studentIdLabel = new Label("Student ID:");
        TextField studentIdTextField = new TextField();

        Label courseIdLabel = new Label("Course ID:");
        TextField courseIdTextField = new TextField();

        Button enrollStudentBtn = new Button("Enroll Student");
        enrollStudentBtn.setOnAction(e -> {
            int studentId = Integer.parseInt(studentIdTextField.getText());
            int courseId = Integer.parseInt(courseIdTextField.getText());

            system.enrollStudent(studentId, courseId);
            showAlert(Alert.AlertType.INFORMATION, "Student enrolled successfully!");
        });

        gridPane.add(studentIdLabel, 0, 0);
        gridPane.add(studentIdTextField, 1, 0);
        gridPane.add(courseIdLabel, 0, 1);
        gridPane.add(courseIdTextField, 1, 1);
        gridPane.add(enrollStudentBtn, 1, 2);

        tab.setContent(gridPane);
        return tab;
    }

    private void showAlert(Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle("Course Management System");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
