package ru.happyshark.java.ee;

import javax.servlet.*;
import java.io.IOException;
import java.util.logging.Logger;

public class MyServlet implements Servlet {
    private static final Logger logger = Logger.getLogger("MyServlet.class");
    private ServletConfig config;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        logger.info("Initializing..");
        config = servletConfig;
    }

    @Override
    public ServletConfig getServletConfig() {
        return config;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        logger.info("Got new request: " + servletRequest.toString());
        servletResponse.getWriter()
                .println("<h1>Hello from the other side</h1>");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
