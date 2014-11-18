import javax.enterprise.inject.Produces;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactoryProducer {
    @Produces
    public EntityManagerFactory createEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("JContactsPersistenceUnit");
    }
}
