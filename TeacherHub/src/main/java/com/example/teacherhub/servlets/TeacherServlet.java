package com.example.teacherhub.servlets;

import com.example.teacherhub.model.Grade;
import com.example.teacherhub.model.Subject;
import com.example.teacherhub.repository.impl.GradeRepositoryImpl;
import com.example.teacherhub.repository.impl.SubjectRepositoryImpl;
import com.example.teacherhub.repository.impl.UserRepositoryImpl;
import com.example.teacherhub.service.GradeService;
import com.example.teacherhub.service.SubjectService;
import com.example.teacherhub.service.UserService;
import com.example.teacherhub.service.impl.GradeServiceImpl;
import com.example.teacherhub.service.impl.SubjectServiceImpl;
import com.example.teacherhub.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.example.teacherhub.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.util.Collections;
import java.util.List;

@WebServlet("/TeacherServlet")
public class TeacherServlet extends HttpServlet {
    private UserService userService;
    private SubjectService subjectService;
    private GradeService gradeService;

    @Override
    public void init() throws ServletException {
        userService = new UserServiceImpl(new UserRepositoryImpl());
        subjectService = new SubjectServiceImpl(new SubjectRepositoryImpl());
        gradeService = new GradeServiceImpl(new GradeRepositoryImpl());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User teacher = (User) request.getSession().getAttribute("user");

        if (teacher != null) {
            String subjectIdParam = request.getParameter("subjectId");
            List<Subject> subjectList = subjectService.getAllSubjects();

            // Sort subjects if requested
            if ("name".equals(request.getParameter("sortSubjects"))) {
                subjectList = subjectService.getSubjectsSortedAlphabetically();
            }

            request.setAttribute("subjects", subjectList);

            if (subjectIdParam != null) {
                int subjectId = Integer.parseInt(subjectIdParam);
                List<Grade> grades = gradeService.getGradesForSubjectAllStudents(subjectId);

                // Sort grades if requested
                if ("name".equals(request.getParameter("sortGrades"))) {
                    grades = gradeService.getGradesSortedByName(subjectId);
                } else if ("number".equals(request.getParameter("sortGrades"))) {
                    grades = gradeService.getGradesSortedByGrade(subjectId);
                } else if ("date".equals(request.getParameter("sortGrades"))) {
                    grades = gradeService.getGradesSortedByDate(subjectId);
                }

                request.setAttribute("grades", grades);
                request.setAttribute("selectedSubject", subjectId);

                // Fetch the selected subject to verify the teacher's assignment
                Subject selectedSubject = subjectList.stream()
                        .filter(subject -> subject.getSubjectID() == subjectId)
                        .findFirst()
                        .orElse(null);

                if (selectedSubject != null && selectedSubject.getTeacherID() == teacher.getUserID()) {
                    List<User> students = subjectService.getStudentsForSubject(subjectId);
                    Collections.sort(students, User.getAlphabeticalComparator());
                    request.setAttribute("students", students); // Add students to the request
                }

                double average = gradeService.calculateAverage(subjectId);
                String formattedAverage = String.format("%.2f", average);
                request.setAttribute("averageGrade", formattedAverage);

            }

            request.getRequestDispatcher("teacherPage.jsp").forward(request, response);
        } else {
            response.sendRedirect("loginPage.jsp"); // If no user is logged in, redirect to login page
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("addGrade".equals(action)) {
            // Logic for adding a grade
            String subjectIdParam = request.getParameter("subjectId");
            String studentIdParam = request.getParameter("studentId");
            String gradeParam = request.getParameter("grade");

            if (subjectIdParam != null && studentIdParam != null && gradeParam != null) {
                int subjectId = Integer.parseInt(subjectIdParam);
                int studentId = Integer.parseInt(studentIdParam);
                double grade = Double.parseDouble(gradeParam);

                boolean success = gradeService.addGradeForStudent(studentId, subjectId, grade);

                if (success) {
                    response.sendRedirect("TeacherServlet?subjectId=" + subjectId + "&success=true");
                } else {
                    response.sendRedirect("TeacherServlet?subjectId=" + subjectId + "&error=true");
                }
            } else {
                response.sendRedirect("teacherPage.jsp?error=Invalid input");
            }
        } else if ("updateGrade".equals(action)) {
            // Logic for updating a grade
            String gradeIdParam = request.getParameter("gradeId");
            String subjectIdParam = request.getParameter("subjectId");
            String updatedGradeParam = request.getParameter("updatedGrade");
            String updatedDateParam = request.getParameter("updatedDate");

            if (gradeIdParam != null && updatedGradeParam != null && updatedDateParam != null) {
                int gradeId = Integer.parseInt(gradeIdParam);
                int subjectId = Integer.parseInt(subjectIdParam);
                double updatedGrade = Double.parseDouble(updatedGradeParam);
                Date updatedDate = Date.valueOf(updatedDateParam);

                boolean success = gradeService.updateGradeForStudent(gradeId, updatedGrade, updatedDate);

                if (success) {
                    response.sendRedirect("TeacherServlet?subjectId=" + subjectId + "&success=true");
                } else {
                    response.sendRedirect("TeacherServlet?subjectId=" + subjectId + "&error=true");
                }
            } else {
                response.sendRedirect("teacherPage.jsp?error=Invalid input");
            }
        } else if ("deleteGrade".equals(action)) {
            String gradeIdParam = request.getParameter("gradeId");
            String subjectIdParam = request.getParameter("subjectId");

            if (gradeIdParam != null) {
                int gradeId = Integer.parseInt(gradeIdParam);
                int subjectId = Integer.parseInt(subjectIdParam);

                boolean success = gradeService.deleteGradeForStudent(gradeId);

                if (success) {
                    response.sendRedirect("TeacherServlet?subjectId=" + subjectId + "&success=true");
                } else {
                    response.sendRedirect("TeacherServlet?subjectId=" + subjectId + "&error=true");
                }
            } else {
                response.sendRedirect("teacherPage.jsp?error=Invalid input");
            }
        } else {
            response.sendRedirect("teacherPage.jsp?error=Unknown action");
        }
    }

}