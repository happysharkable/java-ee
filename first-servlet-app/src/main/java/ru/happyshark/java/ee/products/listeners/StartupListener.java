package ru.happyshark.java.ee.products.listeners;

import ru.happyshark.java.ee.products.persist.Product;
import ru.happyshark.java.ee.products.persist.ProductRepository;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;

@WebListener
public class StartupListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductRepository productRepository = new ProductRepository();

        productRepository.save(new Product(null, "Bread", "Fresh garlic bread", new BigDecimal(100)));
        productRepository.save(new Product(null, "Milk", "No lactose", new BigDecimal(150)));
        productRepository.save(new Product(null, "Eggs", "Dozen of eggs", new BigDecimal(120)));
        productRepository.save(new Product(null, "Cheese", "Straight from Switzerland", new BigDecimal(500)));

        sce.getServletContext().setAttribute("productRepository", productRepository);
    }
}
