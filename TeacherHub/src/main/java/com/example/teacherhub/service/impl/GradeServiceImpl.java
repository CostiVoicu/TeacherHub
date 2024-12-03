package com.example.teacherhub.service.impl;

import com.example.teacherhub.model.Grade;
import com.example.teacherhub.repository.GradeRepository;
import com.example.teacherhub.service.GradeService;

import java.sql.Date;
import java.util.Collections;
import java.util.Comparator;
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

    @Override
    public List<Grade> getGradesSortedByDate(int subjectId) {
        List<Grade> grades = gradeRepository.getGradesForSubjectAllStudents(subjectId);
        grades.sort(Comparator.comparing(Grade::getDateAssigned));
        return grades;
    }

    @Override
    public List<Grade> getGradesSortedByGrade(int subjectId) {
        List<Grade> grades = gradeRepository.getGradesForSubjectAllStudents(subjectId);
        grades.sort(Comparator.comparing(Grade::getGrade).reversed());
        return grades;
    }

    @Override
    public List<Grade> getGradesSortedByName(int subjectId) {
        List<Grade> grades = gradeRepository.getGradesForSubjectAllStudents(subjectId);
        Collections.sort(grades, new Grade.StudentNameComparator());
        return grades;
    }

    @Override
    public double calculateAverage(int subjectId) {
        List<Grade> grades = gradeRepository.getGradesForSubjectAllStudents(subjectId);
        return grades.stream()
                .mapToDouble(Grade::getGrade) // Convert Grade to double
                .average() // Calculate the average
                .orElse(0.0); // Return 0.0 if there are no valid grades
    }
}
