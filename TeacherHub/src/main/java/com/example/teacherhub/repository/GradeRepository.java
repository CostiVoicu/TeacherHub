package com.example.teacherhub.repository;

import com.example.teacherhub.model.Grade;

import java.util.List;

public interface GradeRepository {
    List<Grade> getGradesForSubjectAllStudents(int subjectId);
    boolean addGradeForStudent(int studentId, int subjectId, double grade);

}
