package com.example.teacherhub.repository;

import com.example.teacherhub.model.Grade;
import com.example.teacherhub.model.Subject;
import com.example.teacherhub.model.User;

import java.util.List;

public interface SubjectRepository {
    List<Subject> getSubjectsByStudentId(int studentId);
    List<Subject> getAllSubjects();
    List<Grade> getGradesForSubject(int studentId, int subjectId);
    List<User> getStudentsForSubject(int studentId);
}
