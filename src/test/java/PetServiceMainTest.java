import model.Pet;
import org.junit.Test;
import service.PetServiceMain;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class PetServiceMainTest {
    private final PetServiceMain petServiceMain = new PetServiceMain(new HashMap<>());
    @Test
    public void addTest() {
        Pet pet = new Pet("name", "breed", 1);

        petServiceMain.add(pet);

        assertFalse(petServiceMain.getAll().isEmpty());
    }

    @Test
    public void deleteTest() {
        Pet petFirst = new Pet("nameFirst", "breedFirst", 1);
        Pet petSecond = new Pet("nameSecond", "breedSecond", 1);
        petServiceMain.add(petFirst);
        petServiceMain.add(petSecond);
        String key = petServiceMain.getKeyByPet(petSecond);

        petServiceMain.delete(key);

        assertEquals(1, petServiceMain.getAll().size());
        assertFalse(petServiceMain.getAll().containsKey(key));
    }

    @Test
    public void getAllTest() {
        Pet pet = new Pet("name", "breed", 1);
        petServiceMain.add(pet);

        int expected = 1;

        Map<String, Pet> pets = petServiceMain.getAll();
        int actual = pets.size();

        assertEquals(expected, actual);
    }
}
