package ru.happyshark.java.ee.products;

import ru.happyshark.java.ee.products.persist.Customer;
import ru.happyshark.java.ee.products.persist.CustomerRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(urlPatterns = {"/customer/*"})
public class CustomerServlet extends HttpServlet {

    private static final Pattern pathParam = Pattern.compile("\\/?(delete)?\\/(\\d*)$");

    private CustomerRepository customerRepository;

    @Override
    public void init() throws ServletException {
        customerRepository = (CustomerRepository) getServletContext().getAttribute("customerRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getPathInfo() == null || req.getPathInfo().equals("") || req.getPathInfo().equals("/")) {
            req.setAttribute("customers", customerRepository.findAll());
            getServletContext().getRequestDispatcher("/WEB-INF/views/customer.jsp").forward(req, resp);
        } else if (req.getPathInfo().equals("/new")) {
            getServletContext().getRequestDispatcher("/WEB-INF/views/customer_form.jsp").forward(req, resp);
        } else if (req.getPathInfo().startsWith("/delete/")) {
            Matcher matcher = pathParam.matcher(req.getPathInfo());
            if (matcher.matches()) {
                long id;
                try {
                    id = Long.parseLong(matcher.group(2));
                } catch (NumberFormatException ex) {
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    return;
                }
                customerRepository.delete(id);
                resp.sendRedirect(getServletContext().getContextPath() + "/customer");
            }
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } else {
            Matcher matcher = pathParam.matcher(req.getPathInfo());
            if (matcher.matches()) {
                long id;
                try {
                    id = Long.parseLong(matcher.group(2));
                } catch (NumberFormatException ex) {
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    return;
                }
                Customer customer = customerRepository.findById(id);
                if (customer == null) {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }
                req.setAttribute("customer", customer);
                getServletContext().getRequestDispatcher("/WEB-INF/views/customer_form.jsp").forward(req, resp);
            }
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getPathInfo() == null || req.getPathInfo().equals("") || req.getPathInfo().equals("/")) {

            String strId = req.getParameter("id");
            try {
                Customer customer = new Customer(
                        strId.isEmpty() ? null : Long.parseLong(strId),
                        req.getParameter("name")
                );
                customerRepository.save(customer);
                resp.sendRedirect(getServletContext().getContextPath() + "/customer");
            } catch (NumberFormatException ex) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
