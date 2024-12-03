package com.example.teacherhub.service.impl;

import com.example.teacherhub.model.Grade;
import com.example.teacherhub.model.Subject;
import com.example.teacherhub.model.User;
import com.example.teacherhub.repository.SubjectRepository;
import com.example.teacherhub.repository.impl.SubjectRepositoryImpl;
import com.example.teacherhub.service.SubjectService;

import java.util.List;

public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;

    public SubjectServiceImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public List<Subject> getSubjectsByStudentId(int studentId) {
        return subjectRepository.getSubjectsByStudentId(studentId);
    }

    @Override
    public List<Subject> getAllSubjects() {
        return subjectRepository.getAllSubjects();
    }

    @Override
    public List<Grade> getGradesForSubject(int studentId, int subjectId) {
        return  subjectRepository.getGradesForSubject(studentId, subjectId);
    }

    @Override
    public List<User> getStudentsForSubject(int subjectId) {
        return subjectRepository.getStudentsForSubject(subjectId);
    }
}
