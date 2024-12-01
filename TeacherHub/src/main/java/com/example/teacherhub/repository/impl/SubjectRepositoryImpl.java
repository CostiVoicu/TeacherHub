package com.example.teacherhub.repository.impl;

import com.example.teacherhub.model.Subject;
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
}
