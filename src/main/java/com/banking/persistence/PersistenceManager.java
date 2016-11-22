package com.banking.persistence;

import com.banking.bank.Account;
import com.banking.bank.Customer;
import com.banking.bank.Transaction;
import org.glassfish.jersey.internal.inject.Custom;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.util.List;

/**
 * @author Graham Murray
 * @version 1.0
 */
public class PersistenceManager {

    private EntityManagerFactory emfactory;
    private EntityManager entityManager;
    private CriteriaBuilder criteriaBuilder;

    public PersistenceManager() {
        emfactory = Persistence.createEntityManagerFactory("Bank");
        entityManager = emfactory.createEntityManager();
        criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    /**
     * Gets a Customer by email and returns it
     *
     * @param email
     * @return Customer
     */
    public Customer getCustomerByEmail(String email) throws NoResultException {
        Customer customer;

        TypedQuery<Customer> query = entityManager.createQuery(
                "SELECT c FROM Customer c WHERE c.email = ?1", Customer.class);

        try {
            customer = query.setParameter(1, email).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            customer = null;
        }

        return customer;
    }

    /**
     * Finds an object of either type Account,
     * Customer or Transaction by id. Returns null
     * if an object isn't a valid type
     *
     * @param objectToFind
     * @param id
     * @return Object
     */
    public Object find(Object objectToFind, int id) {
        if (isValidObject(objectToFind)) {
            return entityManager.find(objectToFind.getClass(), id);
        }

        return null;
    }

    /**
     * Persist an object
     * @param o
     */
    public void persist(Object o) {
        entityManager.persist(o);
    }

    /**
     * Commits a entity within a valid transaction
     */
    public void commit() {
        entityManager.getTransaction().commit();
    }

    /**
     * Start a transaction
     */
    public void start() {
        entityManager.getTransaction().begin();
    }

    /**
     * Closes the entity manager factory along with the
     * entity manager. Is used after a PersistenceManager
     * instance is no longer required.
     */
    public void close() {
        emfactory.close();
        entityManager.close();
    }

    private boolean isValidObject(Object o) {
        return o instanceof Account || o instanceof Customer || o instanceof Transaction;
    }
}
