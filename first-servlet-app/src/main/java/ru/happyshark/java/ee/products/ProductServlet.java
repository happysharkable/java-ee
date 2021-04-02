package ru.happyshark.java.ee.products;

import ru.happyshark.java.ee.products.persist.Product;
import ru.happyshark.java.ee.products.persist.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(urlPatterns = {"/product/*"})
public class ProductServlet extends HttpServlet {
    private static final Pattern pathParam = Pattern.compile("\\/?(delete)?\\/(\\d*)$");

    private ProductRepository productRepository;

    @Override
    public void init() throws ServletException {
        productRepository = (ProductRepository) getServletContext().getAttribute("productRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getPathInfo() == null || req.getPathInfo().equals("") || req.getPathInfo().equals("/")) {
            req.setAttribute("products", productRepository.findAll());
            getServletContext().getRequestDispatcher("/WEB-INF/views/product.jsp").forward(req, resp);
        } else if (req.getPathInfo().equals("/new")) {
            getServletContext().getRequestDispatcher("/WEB-INF/views/product_form.jsp").forward(req, resp);
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
                productRepository.delete(id);
                resp.sendRedirect(getServletContext().getContextPath() + "/product");
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
                Product product = productRepository.findById(id);
                if (product == null) {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }
                req.setAttribute("product", product);
                getServletContext().getRequestDispatcher("/WEB-INF/views/product_form.jsp").forward(req, resp);
            }
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getPathInfo() == null || req.getPathInfo().equals("") || req.getPathInfo().equals("/")) {

            String strId = req.getParameter("id");
            try {
                Product product = new Product(
                        strId.isEmpty() ? null : Long.parseLong(strId),
                        req.getParameter("name"),
                        req.getParameter("description"),
                        new BigDecimal(req.getParameter("price")));
                productRepository.save(product);
                resp.sendRedirect(getServletContext().getContextPath() + "/product");
            } catch (NumberFormatException ex) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
