import data.Contact;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import javax.persistence.*;

public class AddContactController {
    @FXML private TextField firstName;
    @FXML private TextField lastName;

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JContactsPersistenceUnit");

    @FXML protected void addContact() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Contact contact = new Contact(firstName.getText(), lastName.getText());

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(contact);
        entityTransaction.commit();

        Main.eventBus.post(new ContactListChangedEvent());
    }
}
