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

        if (teacher != null){
            String subjectIdParam = request.getParameter("subjectId");
            List<Subject> subjectList = subjectService.getAllSubjects();
            request.setAttribute("subjects", subjectList);

            if (subjectIdParam != null){
                int subjectId = Integer.parseInt(subjectIdParam);
                List<Grade> grades = gradeService.getGradesForSubjectAllStudents(subjectId);
                request.setAttribute("grades", grades);
                request.setAttribute("selectedSubject", subjectId);
            }

            request.getRequestDispatcher("teacherPage.jsp").forward(request, response);
        } else {
            response.sendRedirect("loginPage.jsp"); // If no user is logged in, redirect to login page
        }
    }
}
