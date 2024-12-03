package com.example.teacherhub.repository.impl;

import com.example.teacherhub.model.Grade;
import com.example.teacherhub.model.Subject;
import com.example.teacherhub.model.User;
import com.example.teacherhub.repository.SubjectRepository;
import com.example.teacherhub.utils.DBConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SubjectRepositoryImpl implements SubjectRepository {
    @Override
    public List<Subject> getSubjectsByStudentId(int studentId) {
        List<Subject> subjects = new ArrayList<>();
        String sql = "{CALL GetSubjectsByStudent(?)}"; // Assuming you have a stored procedure for this

        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareCall(sql)) {

            preparedStatement.setInt(1, studentId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Subject subject = new Subject();
                    subject.setSubjectID(resultSet.getInt("SubjectID"));
                    subject.setSubjectName(resultSet.getString("SubjectName"));
                    subjects.add(subject);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return subjects;
    }

    @Override
    public List<Subject> getAllSubjects() {
        List<Subject> subjects = new ArrayList<>();

        String sql = "{CALL GetAllSubjects}";

        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareCall(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Subject subject = new Subject();
                subject.setSubjectID(resultSet.getInt("SubjectID"));
                subject.setSubjectName(resultSet.getString("SubjectName"));
                subject.setTeacherID(resultSet.getInt("TeacherID"));
                subjects.add(subject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return subjects;
    }

    @Override
    public List<Grade> getGradesForSubject(int studentId, int subjectId) {
        List<Grade> grades = new ArrayList<>();
        String sql = "{CALL GetGradesForSubject(?, ?)}";

        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareCall(sql)) {

            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(2, subjectId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Grade grade = new Grade();
                    grade.setGradeID(resultSet.getInt("GradeID"));
                    grade.setGrade(resultSet.getDouble("Grade"));
                    grade.setStudentID(resultSet.getInt("StudentID"));
                    grade.setSubjectID(resultSet.getInt("SubjectID"));
                    grade.setDateAssigned(resultSet.getDate("DateAssigned"));
                    grades.add(grade);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return grades;
    }

    @Override
    public List<User> getStudentsForSubject(int subjectId) {
        List<User> students = new ArrayList<>();
        String sql = "{CALL GetStudentsForSubject(?)}"; // Stored procedure call syntax

        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareCall(sql)) {

            preparedStatement.setInt(1, subjectId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    User student = new User();
                    student.setUserID(resultSet.getInt("StudentID"));
                    student.setFirstName(resultSet.getString("FirstName"));
                    student.setLastName(resultSet.getString("LastName"));
                    students.add(student);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }
}
