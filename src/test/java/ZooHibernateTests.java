import database.DatabaseUtils;
import database.hibernate.DBHibernateService;
import database.hibernate.models.Animal;

import database.hibernate.models.Workman;
import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ZooHibernateTests {
    DBHibernateService dbHibernateService = new DBHibernateService();

    @BeforeAll
    static void init() {
        DatabaseUtils.createData();
    }

    @Test
    void insertNewAnimalSuccessfully() {
        Animal newAnimal = new Animal();
        newAnimal.setId(11);
        newAnimal.setName("Barsik");
        newAnimal.setAge(3);
        newAnimal.setType(2);
        newAnimal.setSex(2);
        newAnimal.setPlace(2);

        assertThrows(PersistenceException.class, ()  -> dbHibernateService.insertAnimal(newAnimal));

    }
    @Test
    void insertNullToWorkman() {
        Workman workman = new Workman();
        workman.setId(88);
        workman.setName(null);
        workman.setAge(12);
        workman.setPosition(1);
        assertThrows(PersistenceException.class, () -> dbHibernateService.insertWorkman(workman));
    }

}