package com.example.teacherhub.repository;

import com.example.teacherhub.model.Subject;

import java.util.List;

public interface SubjectRepository {
    List<Subject> getSubjectsByStudentId(int studentId);
}
