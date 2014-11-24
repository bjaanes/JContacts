package com.gjermundbjaanes.services;

import com.gjermundbjaanes.JContactException;
import com.gjermundbjaanes.data.Contact;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class ContactServiceTest {

    @Mock EntityManagerFactory entityManagerFactory;
    @Mock EntityManager entityManager;
    @Mock Query query;
    @Mock EntityTransaction entityTransaction;

    @InjectMocks ContactService contactService;

    @Before public void initMocks() {
        MockitoAnnotations.initMocks(this);
        when(entityManager.createQuery(anyString())).thenReturn(query);
        when(entityManager.getTransaction()).thenReturn(entityTransaction);
        when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);
    }

    @Test
    public void getContactsShouldReturnEmptyListWhenNullQuery() {
        when(query.getResultList()).thenReturn(null);

        ArrayList<Contact> returnedContactList = contactService.getContacts();

        assertNotNull(returnedContactList);
        assertTrue(returnedContactList.isEmpty());
    }

    @Test
    public void getContactsShouldReturnEmptyWhenEmptyQuery() {
        ArrayList<Contact> emptyContactList = new ArrayList<>();
        when(query.getResultList()).thenReturn(emptyContactList);

        ArrayList<Contact> returnedContactList = contactService.getContacts();

        assertNotNull(returnedContactList);
        assertTrue(returnedContactList.isEmpty());
    }

    @Test
    public void getContactsShouldReturnContacts() {
        ArrayList<Contact> contactList = new ArrayList<>();
        addContactsToList(contactList);
        when(query.getResultList()).thenReturn(contactList);

        ArrayList<Contact> returnedContactList = contactService.getContacts();

        assertNotNull(returnedContactList);
        assertFalse(returnedContactList.isEmpty());
        assertEquals(contactList.size(), returnedContactList.size());
        assertEquals(contactList.get(0), returnedContactList.get(0));
    }

    private void addContactsToList(ArrayList<Contact> contactList) {
        contactList.add(new Contact());
        contactList.add(new Contact());
    }

    @Test(expected = JContactException.class)
    public void addContactShouldFailWithNull() throws JContactException {
        contactService.addContact(null);
    }
}