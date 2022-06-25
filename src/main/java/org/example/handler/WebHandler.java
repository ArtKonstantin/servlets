package org.example.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface WebHandler {
    void handle(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException;
}
