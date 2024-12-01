package com.example.teacherhub.repository;

import com.example.teacherhub.model.User;

import java.util.List;

public interface UserRepository {
    List<User> getAllTeachers();
    List<User> getAllStudents();
}
