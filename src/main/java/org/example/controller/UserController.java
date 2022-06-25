package org.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.manager.UserManager;

import java.io.IOException;

@RequiredArgsConstructor
public class UserController {
    private final UserManager manager;

    public void getAll(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        res.getWriter().write("getAll");
    }

    public void getById(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        res.getWriter().write("getById");
    }
}
