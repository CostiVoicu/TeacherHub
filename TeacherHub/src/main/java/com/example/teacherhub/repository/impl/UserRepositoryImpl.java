package com.example.teacherhub.repository.impl;

import com.example.teacherhub.model.User;
import com.example.teacherhub.repository.UserRepository;
import com.example.teacherhub.utils.DBConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    @Override
    public List<User> getAllTeachers() {
        List<User> teachers = new ArrayList<>();
        String sql = "{CALL GetAllTeachers}"; // Stored procedure call

        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareCall(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                User teacher = new User();
                teacher.setUserID(resultSet.getInt("UserID"));
                teacher.setFirstName(resultSet.getString("FirstName"));
                teacher.setLastName(resultSet.getString("LastName"));
                teacher.setUserType("Teacher");
                teacher.setEmail(resultSet.getString("Email"));
                teacher.setPassword(resultSet.getString("Password"));
                teachers.add(teacher);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return teachers;
    }
    @Override
    public List<User> getAllStudents() {
        List<User> students = new ArrayList<>();
        String sql = "{CALL GetAllStudents}"; // Stored procedure call

        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareCall(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                User student = new User();
                student.setUserID(resultSet.getInt("UserID"));
                student.setFirstName(resultSet.getString("FirstName"));
                student.setLastName(resultSet.getString("LastName"));
                student.setUserType("Student");
                student.setEmail(resultSet.getString("Email"));
                student.setPassword(resultSet.getString("Password"));
                students.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }
}
