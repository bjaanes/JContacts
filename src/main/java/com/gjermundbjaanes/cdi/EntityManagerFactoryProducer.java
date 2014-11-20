package com.gjermundbjaanes.cdi;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

public class EntityManagerFactoryProducer {
    @Produces
    public EntityManagerFactory createEntityManagerFactory() throws Exception {
        try {
            return Persistence.createEntityManagerFactory("JContactsPersistenceUnit");
        } catch (PersistenceException e) {
            throw new Exception("Persistence could not be set up");
        }
    }
}
