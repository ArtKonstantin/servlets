package org.example.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.attribute.RequestAttributes;
import org.example.security.Authentication;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@WebFilter("/*")
@Slf4j
public class AuthFilter extends HttpFilter {
    private final Map<String, String> users = Map.of (
            "vasya", "secret",
            "petya", "secret"
    );

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String login = req.getHeader("X-Login");
        String password = req.getHeader("X-Password");
        log.debug("login: {}, password: {}", login, password);

        if (login == null) {
            res.setStatus(401);
            res.getWriter().write("Not authenticated");
            return;
        }

        if (password == null) {
            res.setStatus(401);
            res.getWriter().write("Not authenticated");
            return;
        }

        if (!Objects.equals(users.get(login), password)) {
            res.setStatus(401);
            res.getWriter().write("Not authenticated");
            return;
        }

        Authentication authentication = new Authentication(login);
        req.setAttribute(RequestAttributes.AUTHENTICATION_ATTR, authentication);

        chain.doFilter(req, res);
    }
}
