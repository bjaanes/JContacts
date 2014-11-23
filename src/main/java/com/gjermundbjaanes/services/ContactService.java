package com.gjermundbjaanes.services;

import com.gjermundbjaanes.JContactException;
import com.gjermundbjaanes.data.Contact;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

@Singleton
public class ContactService extends Observable {

    @Inject
    EntityManagerFactory entityManagerFactory;

    public void addContact(Contact contact) throws JContactException {
        if (contact != null) {
            EntityManager entityManager = entityManagerFactory.createEntityManager();

            EntityTransaction entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            entityManager.persist(contact);
            entityTransaction.commit();

            setChanged();
            notifyObservers();
        } else {
            throw new JContactException("Contact is null");
        }
    }

    public ArrayList<Contact> getContacts() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        ArrayList<Contact> contactList = new ArrayList<>();

        Query query = entityManager.createQuery("SELECT t FROM Contact t");
        List resultList = query.getResultList();
        if (resultList != null) {
            for (Object contact : resultList) {
                Contact resultContact = (Contact) contact;
                contactList.add(resultContact);
            }
        }

        return contactList;
    }
}
