package com.example.teacherhub.service.impl;

import com.example.teacherhub.model.Grade;
import com.example.teacherhub.repository.GradeRepository;
import com.example.teacherhub.service.GradeService;

import java.util.List;

public class GradeServiceImpl implements GradeService {
    private final GradeRepository gradeRepository;
    public GradeServiceImpl(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    @Override
    public List<Grade> getGradesForSubjectAllStudents(int subjectId) {
        return gradeRepository.getGradesForSubjectAllStudents(subjectId);
    }
}
