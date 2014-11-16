import data.Contact;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import javax.persistence.*;

public class AddContactController {
    @FXML private TextField firstname;
    @FXML private TextField lastname;

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JContactsPersistenceUnit");
    private MainWindowController mainWindowController;

    @FXML protected void addContact() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Contact contact = new Contact(firstname.getText(), lastname.getText());

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(contact);
        entityTransaction.commit();

        if (mainWindowController != null) {
            mainWindowController.refreshContacts();
        }
    }

    public void setMainWindowController(MainWindowController mainWindowController) {
        this.mainWindowController = mainWindowController;
    }
}
