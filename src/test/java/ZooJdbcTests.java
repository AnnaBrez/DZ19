import database.Animal;
import database.DatabaseUtils;
import database.CRUDUtils;
import database.jdbc.DatabaseConnection;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ZooJdbcTests {

    @BeforeAll
    static void init() {
        DatabaseUtils.createData();
    }

    @AfterAll
    static void tearDown() {
        DatabaseConnection.closeConnection();
    }

    @Test
    void insertNewAnimalWithId11() throws SQLException {

        Animal newAnimal = new Animal();
        newAnimal.setId(11);
        newAnimal.setName("Барсик");
        newAnimal.setAge(3);
        newAnimal.setType(2);
        newAnimal.setSex(2);
        newAnimal.setPlace(2);


        int countBefore = CRUDUtils.getAnimalCountRow();

        CRUDUtils.insertAnimalData(newAnimal);

        assertEquals(countBefore + 1, CRUDUtils.getAnimalCountRow());

    }
    @Test
    void updateExistingAnimal() throws SQLException {

        Animal animal = CRUDUtils.getAnimalById(11);
        assertNotNull(animal, "Запись с id=1 должна существовать");


        animal.setName("Новое имя");
        animal.setAge(5);

        boolean updateResult = CRUDUtils.updateAnimalData(animal);
        assertTrue(updateResult, "Обновление должно пройти успешно");

        Animal updatedAnimal = CRUDUtils.getAnimalById(1);
        assertEquals("Новое имя", updatedAnimal.getName());
        assertEquals(5, updatedAnimal.getAge());
    }
}