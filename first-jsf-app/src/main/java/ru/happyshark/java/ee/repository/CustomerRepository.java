package ru.happyshark.java.ee.repository;

import ru.happyshark.java.ee.persist.Customer;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
@Named
public class CustomerRepository {
    private final Map<Long, Customer> customerMap = new ConcurrentHashMap<>();

    private final AtomicLong identity = new AtomicLong(0);

    @PostConstruct
    public void init() {
        save(new Customer(null, "Steve Jobs"));
        save(new Customer(null, "Mario"));
        save(new Customer(null, "Bowser"));
        save(new Customer(null, "Luigi"));
    }

    public void save(Customer customer) {
        if (customer.getId() == null) {
            customer.setId(identity.incrementAndGet());
        }
        customerMap.put(customer.getId(), customer);
    }

    public void delete(Long id) {
        customerMap.remove(id);
    }

    public Customer findById(Long id) {
        return customerMap.get(id);
    }

    public List<Customer> findAll() {
        return new ArrayList<>(customerMap.values());
    }
}
