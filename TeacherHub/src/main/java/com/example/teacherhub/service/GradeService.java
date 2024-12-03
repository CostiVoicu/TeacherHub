package com.example.teacherhub.service;

import com.example.teacherhub.model.Grade;

import java.util.List;

public interface GradeService {
    List<Grade> getGradesForSubjectAllStudents(int subjectId);
    boolean addGradeForStudent(int studentId, int subjectId, double grade);
}
