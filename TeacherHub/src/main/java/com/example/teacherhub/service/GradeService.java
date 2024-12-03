package com.example.teacherhub.service;

import com.example.teacherhub.model.Grade;

import java.util.List;

public interface GradeService {
    List<Grade> getGradesForSubjectAllStudents(int subjectId);
}
