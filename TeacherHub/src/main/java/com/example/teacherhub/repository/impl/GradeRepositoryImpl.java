package com.example.teacherhub.repository.impl;

import com.example.teacherhub.model.Grade;
import com.example.teacherhub.repository.GradeRepository;
import com.example.teacherhub.utils.DBConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GradeRepositoryImpl implements GradeRepository {
    @Override
    public List<Grade> getGradesForSubjectAllStudents(int subjectId) {
        List<Grade> grades = new ArrayList<>();
        String sql = "{CALL GetGradesForSubjectAllStudents(?)}";

        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareCall(sql)) {

            preparedStatement.setInt(1, subjectId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Grade grade = new Grade();
                    grade.setGradeID(resultSet.getInt("GradeID"));
                    grade.setGrade(resultSet.getDouble("Grade"));
                    grade.setStudentID(resultSet.getInt("StudentID"));
                    grade.setSubjectID(resultSet.getInt("SubjectID"));
                    grade.setDateAssigned(resultSet.getDate("DateAssigned"));
                    grade.setStudentName(resultSet.getString("StudentName"));
                    grades.add(grade);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return grades;
    }
}
