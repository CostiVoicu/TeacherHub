package com.example.teacherhub.service;

import com.example.teacherhub.model.Grade;

import java.sql.Date;
import java.util.List;

public interface GradeService {
    List<Grade> getGradesForSubjectAllStudents(int subjectId);
    boolean addGradeForStudent(int studentId, int subjectId, double grade);
    boolean updateGradeForStudent(int gradeId, double grade, Date dateAssigned);
    boolean deleteGradeForStudent(int gradeId);
    List<Grade> getGradesSortedByDate(int subjectId);
    List<Grade> getGradesSortedByGrade(int subjectId);
    List<Grade> getGradesSortedByName(int subjectId);
    double calculateAverage(int subjectId);
}
