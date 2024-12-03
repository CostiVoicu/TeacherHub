package com.example.teacherhub.servlets;

import com.example.teacherhub.model.User;
import com.example.teacherhub.repository.UserRepository;
import com.example.teacherhub.repository.impl.UserRepositoryImpl;
import com.example.teacherhub.service.UserService;
import com.example.teacherhub.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private UserService userService;
    @Override
    public void init() throws ServletException {
        userService = new UserServiceImpl(new UserRepositoryImpl());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");


        List<User> teachers = userService.getAllTeachers();
        List<User> students = userService.getAllStudents();

        // Check if the user is a teacher
        for (User teacher : teachers) {
            if (teacher.getEmail().equals(email) && teacher.getPassword().equals(password)) {
                request.getSession().setAttribute("user", teacher);
                response.sendRedirect("TeacherServlet"); // Redirect to teacher page
                return;
            }
        }

        // Check if the user is a student
        for (User student : students) {
            if (student.getEmail().equals(email) && student.getPassword().equals(password)) {
                request.getSession().setAttribute("user", student); // Store user in session
                response.sendRedirect("StudentServlet");
                return;
            }
        }

        // If no match, redirect to log in with error
        response.sendRedirect("loginPage.jsp?error=Invalid credentials");
    }
}
