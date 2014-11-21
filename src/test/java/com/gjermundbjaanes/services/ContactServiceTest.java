package com.gjermundbjaanes.services;

import com.gjermundbjaanes.data.Contact;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class ContactServiceTest {

    @Mock EntityManagerFactory entityManagerFactory;
    @Mock EntityManager entityManager;
    @Mock Query query;

    @InjectMocks ContactService contactService;

    @Before public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnEmptyListOnNullQuery() {
        // Given
        when(query.getResultList()).thenReturn(null);
        when(entityManager.createQuery(anyString())).thenReturn(query);
        when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);

        // When
        ArrayList<Contact> returnedContactList = contactService.getContacts();

        // Then
        assertNotNull(returnedContactList);
        assertTrue(returnedContactList.isEmpty());
    }
}