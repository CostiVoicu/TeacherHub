package com.example.teacherhub.service;

import com.example.teacherhub.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllTeachers();
    List<User> getAllStudents();
}
