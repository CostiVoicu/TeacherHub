package com.example.teacherhub.service;

import com.example.teacherhub.model.Subject;

import java.util.List;

public interface SubjectService {
    List<Subject> getSubjectsByStudentId(int studentId);
}
