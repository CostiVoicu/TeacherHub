package com.example.teacherhub.servlets;

import com.example.teacherhub.model.Grade;
import com.example.teacherhub.model.Subject;
import com.example.teacherhub.repository.impl.SubjectRepositoryImpl;
import com.example.teacherhub.repository.impl.UserRepositoryImpl;
import com.example.teacherhub.service.SubjectService;
import com.example.teacherhub.service.UserService;
import com.example.teacherhub.service.impl.SubjectServiceImpl;
import com.example.teacherhub.service.impl.UserServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import com.example.teacherhub.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
    private UserService userService;
    private SubjectService subjectService;
    @Override
    public void init() throws ServletException {
        userService = new UserServiceImpl(new UserRepositoryImpl());
        subjectService = new SubjectServiceImpl(new SubjectRepositoryImpl());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User student = (User) request.getSession().getAttribute("user");

        if (student != null) {
            String subjectIdParam = request.getParameter("subjectId");
            List<Subject> subjects = subjectService.getSubjectsByStudentId(student.getUserID());
            request.setAttribute("subjects", subjects);

            if (subjectIdParam != null) {
                // If a subject is selected, fetch the grades for that subject
                int subjectId = Integer.parseInt(subjectIdParam);
                List<Grade> grades = subjectService.getGradesForSubject(student.getUserID(), subjectId);
                request.setAttribute("grades", grades);
                request.setAttribute("selectedSubject", subjectId);  // To highlight the selected subject
            }

            // Forward to the student page, with either subjects or grades displayed
            request.getRequestDispatcher("studentPage.jsp").forward(request, response);
        } else {
            response.sendRedirect("login.jsp"); // If no user is logged in, redirect to login page
        }
    }
}
