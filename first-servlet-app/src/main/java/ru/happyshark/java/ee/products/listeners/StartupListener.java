package ru.happyshark.java.ee.products.listeners;

import ru.happyshark.java.ee.products.persist.*;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;

@WebListener
public class StartupListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        initProductRepository(sce);
        initCategoryRepository(sce);
        initCustomerRepository(sce);
    }

    private void initProductRepository(ServletContextEvent sce) {
        ProductRepository productRepository = new ProductRepository();

        productRepository.save(new Product(null, "Bread", "Fresh garlic bread", new BigDecimal(100)));
        productRepository.save(new Product(null, "Milk", "No lactose", new BigDecimal(150)));
        productRepository.save(new Product(null, "Eggs", "Dozen of eggs", new BigDecimal(120)));
        productRepository.save(new Product(null, "Cheese", "Straight from Switzerland", new BigDecimal(500)));

        sce.getServletContext().setAttribute("productRepository", productRepository);
    }

    private void initCategoryRepository(ServletContextEvent sce) {
        CategoryRepository categoryRepository = new CategoryRepository();

        categoryRepository.save(new Category(null, "Groceries"));
        categoryRepository.save(new Category(null, "Dairy products"));
        categoryRepository.save(new Category(null, "Meat"));
        categoryRepository.save(new Category(null, "House stuff"));

        sce.getServletContext().setAttribute("categoryRepository", categoryRepository);
    }

    private void initCustomerRepository(ServletContextEvent sce) {
        CustomerRepository customerRepository = new CustomerRepository();

        customerRepository.save(new Customer(null, "Bob Johnson"));
        customerRepository.save(new Customer(null, "Alice Cooper"));
        customerRepository.save(new Customer(null, "Jack White"));
        customerRepository.save(new Customer(null, "Jimmy Hendrix"));

        sce.getServletContext().setAttribute("customerRepository", customerRepository);
    }

}
