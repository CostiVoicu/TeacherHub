package com.example.teacherhub.service.impl;

import com.example.teacherhub.model.User;
import com.example.teacherhub.repository.UserRepository;
import com.example.teacherhub.repository.impl.UserRepositoryImpl;
import com.example.teacherhub.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    // Constructor with Dependency Injection (manual or via framework)
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllTeachers() {
        // Add logging
        System.out.println("Fetching all teachers...");
        List<User> teachers = userRepository.getAllTeachers();

        // Optional: Validation or transformation
        if (teachers.isEmpty()) {
            System.out.println("No teachers found.");
        }

        return teachers;
    }

    @Override
    public List<User> getAllStudents() {
        // Add logging
        System.out.println("Fetching all students...");
        List<User> students = userRepository.getAllStudents();

        // Optional: Validation or transformation
        if (students.isEmpty()) {
            System.out.println("No students found.");
        }

        return students;
    }
}
