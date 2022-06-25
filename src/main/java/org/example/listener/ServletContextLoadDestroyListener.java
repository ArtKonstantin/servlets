package org.example.listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.example.attribute.ContextAttributes;
import org.example.controller.UserController;
import org.example.handler.WebHandler;
import org.example.manager.UserManager;

import java.util.Map;

@WebListener
public class ServletContextLoadDestroyListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        UserManager userManager = new UserManager();
        UserController userController = new UserController(userManager);

        Map<String, WebHandler> handlers = Map.of(
                "/users.getAll", userController::getAll,
                "/users.getById", userController::getById
        );

        servletContext.setAttribute(ContextAttributes.HANDLERS_CONTEXT_ATTR, handlers);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
