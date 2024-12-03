package com.example.teacherhub.repository;

import com.example.teacherhub.model.Grade;
import com.example.teacherhub.model.Subject;

import java.util.List;

public interface SubjectRepository {
    List<Subject> getSubjectsByStudentId(int studentId);
    List<Grade> getGradesForSubject(int studentId, int subjectId);
}
