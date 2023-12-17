package service;
import model.Pet;

import java.util.Map;
public class PetServiceMain implements PetService{
    private final Map<String, Pet> pets;

    public PetServiceMain(Map<String, Pet> pets) {
        this.pets = pets;
    }

    @Override
    public void add(Pet pet) {
        try {
            if (pets != null) {
                String key = Integer.toString(pets.size() + 1);
                pets.put(key, pet);
            }
        } catch (RuntimeException e) {
            throw new IllegalArgumentException("Pet could not be added");
        }
    }

    @Override
    public void delete(String key) {
        try {
            if (pets != null) {
                pets.remove(key);
            }
        } catch (RuntimeException e) {
            throw new IllegalArgumentException("Pet with key " + key + " could be delete.");
        }
    }

    @Override
    public Map<String, Pet> getAll() {
        return pets;
    }

    @Override
    public String getKeyByPet(Pet searchPet) {
        for (Map.Entry<String, Pet> pet : pets.entrySet()) {
            if (searchPet.equals(pet.getValue())) {
                return pet.getKey();
            }
        }
        return null;
    }
}
