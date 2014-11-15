import data.Contact;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import javax.persistence.*;
import java.util.List;

public class JContactsController {
    @FXML private Text actiontarget;
    @FXML private TextField firstname;
    @FXML private TextField lastname;
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JContactsPersistenceUnit");

    @FXML protected void handleSubmitButtonAction(ActionEvent event) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Contact contact = new Contact(firstname.getText(), lastname.getText());

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(contact);
        entityTransaction.commit();


        Query query = entityManager.createQuery("SELECT t FROM Contact t");
        List resultList = query.getResultList();
        for (int i = 0; i < resultList.size(); ++i) {
            Contact resultContact = (Contact)resultList.get(i);
            System.out.println("#" + i + ": " + resultContact.getFirstname() + " " + resultContact.getLastname() + "(" + resultContact.getId() + ")");
        }
    }

}