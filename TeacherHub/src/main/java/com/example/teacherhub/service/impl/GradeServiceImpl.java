package com.example.teacherhub.service.impl;

import com.example.teacherhub.model.Grade;
import com.example.teacherhub.repository.GradeRepository;
import com.example.teacherhub.service.GradeService;

import java.sql.Date;
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

    @Override
    public boolean addGradeForStudent(int studentId, int subjectId, double grade) {
        if (grade < 0 || grade > 10) {
            throw new IllegalArgumentException("Grade must be between 0 and 100.");
        }

        return gradeRepository.addGradeForStudent(studentId, subjectId, grade);
    }

    @Override
    public boolean updateGradeForStudent(int gradeId, double grade, Date dateAssigned) {
        if (grade < 0 || grade > 10) {
            throw new IllegalArgumentException("Grade must be between 0 and 100.");
        }

        return gradeRepository.updateGradeForStudent(gradeId, grade, dateAssigned);
    }

    @Override
    public boolean deleteGradeForStudent(int gradeId) {
        return gradeRepository.deleteGradeForStudent(gradeId);
    }
}
