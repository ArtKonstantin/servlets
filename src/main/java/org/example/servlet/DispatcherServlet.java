package org.example.servlet;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.attribute.ContextAttributes;
import org.example.handler.WebHandler;

import java.io.IOException;
import java.util.Map;

@WebServlet(value = "/", loadOnStartup = 1)
@Slf4j
public class DispatcherServlet extends HttpServlet {
    private Map<String, WebHandler> handlers;

    @Override
    public void init() throws ServletException {
        handlers = (Map<String, WebHandler>) getServletContext().getAttribute(ContextAttributes.HANDLERS_CONTEXT_ATTR);
    }

    @Override
    protected void service(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getRequestURI().substring(req.getContextPath().length());

        WebHandler handler = handlers.get(path);
        if (handler == null) {
            resp.setStatus(404);
            resp.getWriter().write("Not found");
            return;
        }
        handler.handle(req, resp);
    }
}
