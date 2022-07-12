package controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import model.Student;
import util.CrudUtil;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class StudentDashBoardFormController {
    public TextField txtStudentId;
    public TextField txtStudentAddress;
    public TextField txtStudentContact;
    public TextField txtStudentEmail;
    public TextField txtStudentName;
    public TextField txtStudentNic;
    public TableView tblStudent;
    public TableColumn colStudentId;
    public TableColumn colEmail;
    public TableColumn colContact;
    public TableColumn colAddress;
    public TableColumn colNic;
    public TableColumn colOption;
    public TableColumn colSName;
    public Label lblDate;
    public Label lblTime;


    public void initialize() {

        colStudentId.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        colSName.setCellValueFactory(new PropertyValueFactory<>("student_name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));

        try {
            LoadAllStudent();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        LoadDateAndTime();
    }

    private void LoadAllStudent() throws SQLException, ClassNotFoundException {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM Student");

            ObservableList<Student> obList1=FXCollections.observableArrayList();
            while (resultSet.next()){
                obList1.add(
                        new Student(
                                resultSet.getString("student_id"),
                                resultSet.getString("student_name"),
                                resultSet.getString("email"),
                                resultSet.getString("contact"),
                                resultSet.getString("address"),
                                resultSet.getString("nic")
                        )
                );
            }
            tblStudent.setItems(obList1);


    }

    public void saveOnAction(ActionEvent actionEvent) {
        Student s=new Student(
                txtStudentId.getText(),txtStudentName.getText(),txtStudentEmail.getText(),txtStudentContact.getText(),txtStudentAddress.getText(),txtStudentNic.getText()
        );
        try {
            if (CrudUtil.execute("INSERT INTO Student VALUES(?,?,?,?,?,?)", s.getStudent_id(), s.getStudent_name(), s.getEmail(), s.getContact(), s.getAddress(), s.getNic())) {
                new Alert(Alert.AlertType.CONFIRMATION, "Save Customer!..").show();
                LoadAllStudent();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "Something went Wrong!..").show();
            }
        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        tblStudent.refresh();
        ClearField();

    }

    public void UpdateOnAction(ActionEvent actionEvent) {
        Student s=new Student(
                txtStudentId.getText(),txtStudentName.getText(),txtStudentEmail.getText(),txtStudentContact.getText(),txtStudentAddress.getText(),txtStudentNic.getText()
        );
        try {
            if (CrudUtil.execute("UPDATE Student SET student_name=?,email=?,contact=?,address=?,nic=? WHERE student_id=?",s.getStudent_name(),s.getEmail(),s.getContact(),s.getAddress(),s.getNic(),s.getStudent_id())){
                new Alert(Alert.AlertType.CONFIRMATION, "Updated!..").show();
                LoadAllStudent();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something Wrong!..").show();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        tblStudent.refresh();
        ClearField();
    }

    public void DeleteOnAction(ActionEvent actionEvent) {
        try {
            if (CrudUtil.execute("DELETE FROM Student WHERE student_id=?",txtStudentId.getText())) {
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted!..").show();
                LoadAllStudent();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        tblStudent.refresh();
        ClearField();
    }

    public void SearchStudentOnACtion(ActionEvent actionEvent) {
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM Student WHERE student_id=?",txtStudentId.getText());
            if (resultSet.next()){
                txtStudentName.setText(resultSet.getString(2));
                txtStudentEmail.setText(resultSet.getString(3));
                txtStudentContact.setText(resultSet.getString(4));
                txtStudentAddress.setText(resultSet.getString(5));
                txtStudentNic.setText(resultSet.getString(6));
            }else {
                new Alert(Alert.AlertType.WARNING,"Empty Result!..").show();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void ClearField(){
        txtStudentId.clear();
        txtStudentName.clear();
        txtStudentEmail.clear();
        txtStudentContact.clear();
        txtStudentAddress.clear();
        txtStudentNic.clear();
    }

    public void clearTextFieldOnAction(ActionEvent actionEvent) {
        ClearField();
    }

    private void LoadDateAndTime() {
        lblDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, event -> {
            LocalTime currentTime = LocalTime.now();
            lblTime.setText(currentTime.getHour() + ":" +
                    currentTime.getMinute() + ":" +
                    currentTime.getSecond());
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    public void onKeyReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            TextField.getClassCssMetaData();
        }
    }
}


