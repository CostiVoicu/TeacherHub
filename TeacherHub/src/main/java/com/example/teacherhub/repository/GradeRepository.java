package com.example.teacherhub.repository;

import com.example.teacherhub.model.Grade;

import java.util.List;

public interface GradeRepository {
    List<Grade> getGradesForSubjectAllStudents(int subjectId);
}
