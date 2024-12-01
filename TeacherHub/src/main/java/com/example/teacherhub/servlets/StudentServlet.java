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
            // Retrieve the student's subjects (example)
            List<Subject> subjects = subjectService.getSubjectsByStudentId(student.getUserID());

            // Set the subjects as an attribute to be accessed in the JSP
            request.setAttribute("subjects", subjects);

            // Forward to the student page
            request.getRequestDispatcher("studentPage.jsp").forward(request, response);
        } else {
            response.sendRedirect("login.jsp"); // If no user is logged in, redirect to login page
        }
    }
}
