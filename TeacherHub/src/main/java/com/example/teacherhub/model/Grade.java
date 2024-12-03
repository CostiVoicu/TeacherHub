package com.example.teacherhub.model;

import java.sql.Date;
import java.util.Comparator;

public class Grade {
    private int gradeID;
    private int studentID;
    private int subjectID;
    private double grade;
    private String studentName;
    private Date dateAssigned;

    public static class StudentNameComparator implements Comparator<Grade> {
        @Override
        public int compare(Grade grade1, Grade grade2) {
            // Split the student names into first and last names
            String[] name1 = grade1.getStudentName().split(" ");
            String[] name2 = grade2.getStudentName().split(" ");

            String firstName1 = name1[1];
            String lastName1 = name1.length > 1 ? name1[0] : "";
            String firstName2 = name2[1];
            String lastName2 = name2.length > 1 ? name2[0] : "";

            int lastNameComparison = lastName1.compareTo(lastName2);
            if (lastNameComparison != 0) {
                return lastNameComparison;
            }

            return firstName1.compareTo(firstName2);
        }
    }
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getGradeID() {
        return gradeID;
    }

    public void setGradeID(int gradeID) {
        this.gradeID = gradeID;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public Date getDateAssigned() {
        return dateAssigned;
    }

    public void setDateAssigned(Date dateAssigned) {
        this.dateAssigned = dateAssigned;
    }
}

