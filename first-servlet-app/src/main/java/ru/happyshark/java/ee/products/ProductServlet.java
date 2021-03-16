package ru.happyshark.java.ee.products;

import org.apache.log4j.Logger;
import ru.happyshark.java.ee.products.persist.Product;
import ru.happyshark.java.ee.products.persist.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.happyshark.java.ee.products.utils.UI.*;

@WebServlet(urlPatterns = {"/products/*"})
public class ProductServlet extends HttpServlet {
    private String contextPath;
    private ProductRepository productRepository;

    @Override
    public void init() throws ServletException {
        productRepository = (ProductRepository) getServletContext().getAttribute("productRepository");
        contextPath = getServletContext().getContextPath() + "/products/";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().append(getBootstrap())
                        .append(getHtmlTag(true, "div", "container"))
                        .append(getHtmlTag(true, "table", "table table-striped"));

        Long id = getProductIdFromRequestURI(req.getRequestURI());
        if (id == null) {
            displayAllProducts(resp);
        } else {
            displayProductById(id, resp);
        }

        resp.getWriter().append(getHtmlTag(false, "table", ""))
                        .append(getHtmlTag(false, "div", ""))
                        .append(getHtmlTag(false, "body", ""))
                        .append(getHtmlTag(false, "html", ""));
    }

    private void displayAllProducts(HttpServletResponse resp) throws IOException {
        resp.getWriter().append(getTableRow(true, "Id", "Name", "Description", "Price"));
        for (Product product : productRepository.findAll()) {
            resp.getWriter().append(getTableRow(false, getHref(contextPath + product.getId(), product.getId().toString()),
                    product.getName(),
                    product.getDescription(),
                    product.getPrice()));
        }
    }

    private void displayProductById(Long id, HttpServletResponse resp) throws IOException {
        Product product = productRepository.findById(id);
        resp.getWriter().append(getTableRow(true, "Key", "Value"))
                .append(getTableRow(false, "ID", product.getId()))
                .append(getTableRow(false, "Name", product.getName()))
                .append(getTableRow(false, "Description", product.getDescription()))
                .append(getTableRow(false, "Price", product.getPrice()))
                .append(getTableRow(false, getHref(contextPath, "Go Back"), ""));
    }

    private Long getProductIdFromRequestURI(String requestURI) {
        String requestedId = String.copyValueOf(requestURI.toCharArray(), contextPath.length(), requestURI.length() - contextPath.length());
        return (requestedId.equals("") ? null : Long.parseLong(requestedId));
    }
}
