package com.example.teacherhub.repository;

import com.example.teacherhub.model.Grade;

import java.sql.Date;
import java.util.List;

public interface GradeRepository {
    List<Grade> getGradesForSubjectAllStudents(int subjectId);
    boolean addGradeForStudent(int studentId, int subjectId, double grade);
    boolean updateGradeForStudent(int gradeId, double grade, Date dateAssigned);

}
