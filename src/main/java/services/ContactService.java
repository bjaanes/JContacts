package services;

import data.Contact;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

@Singleton
public class ContactService extends Observable {

    @Inject private EntityManagerFactory entityManagerFactory;

    public void addContact(Contact contact) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(contact);
        entityTransaction.commit();

        setChanged();
        notifyObservers();
    }

    public ArrayList<Contact> getContacts() {
        ArrayList<Contact> contactList = new ArrayList<>();

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT t FROM Contact t");
        List resultList = query.getResultList();
        for (int i = 0; i < resultList.size(); ++i) {
            Contact resultContact = (Contact)resultList.get(i);
            contactList.add(resultContact);
        }

        return contactList;
    }
}
