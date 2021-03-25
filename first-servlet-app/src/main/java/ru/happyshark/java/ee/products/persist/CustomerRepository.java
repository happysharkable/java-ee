package ru.happyshark.java.ee.products.persist;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class CustomerRepository {
    private final Map<Long, Customer> customers = new ConcurrentHashMap<>();
    private final AtomicLong id = new AtomicLong(0);

    public void save(Customer customer) {
        if (customer.getId() == null) {
            customer.setId(id.incrementAndGet());
        }
        customers.put(customer.getId(), customer);
    }

    public void delete(Long id) {
        customers.remove(id);
    }

    public Customer findById(Long id) {
        return customers.get(id);
    }

    public List<Customer> findAll() {
        return new ArrayList<>(customers.values());
    }
}
