package com.banking.persistence;

import com.banking.bank.Account;
import com.banking.bank.Customer;
import com.banking.bank.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
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

    public Customer criteria(String e) {

        TypedQuery<Customer> q = entityManager.createQuery(
                "SELECT * " +
                        "FROM Customer" +
                        "WHERE \"email\" = ?", Customer.class).setParameter("email", e);
        List<Customer> results =   q.getResultList();

        return results.get(0);
    }

    private boolean isValidObject(Object o) {
        return o instanceof Account || o instanceof Customer || o instanceof Transaction;
    }
}
