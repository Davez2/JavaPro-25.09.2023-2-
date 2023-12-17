import com.fasterxml.jackson.databind.json.JsonMapper;
import model.Pet;
import model.Shelter;
import service.PetService;
import service.PetServiceMain;
import service.serializer.Format;
import service.serializer.PetSerializer;
import view.Command;
import view.Menu;

import java.util.Optional;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        PetSerializer petSerializer = new PetSerializer(new JsonMapper(), Format.JSON);
        Shelter shelter = new Shelter();

        try {
            Optional<Shelter> shelterData = petSerializer.deserialize();
            if (shelterData.isPresent()) {
                shelter = shelterData.get();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        boolean exit = false;
        try (Scanner scanner = new Scanner(System.in)) {
            while (!exit) {
                try {
                    Menu.showMenu();

                    String input = scanner.next();
                    if (Command.EXIT.getAction().equals(input)) {
                        exit = true;
                        petSerializer.serialize(shelter);
                        continue;
                    }

                    PetService petService = new PetServiceMain(shelter.getPets());

                    if (Command.ADD.getAction().equals(input)) {
                        Pet generatedPet = Menu.createPet(scanner);
                        petService.add(generatedPet);
                    }

                    if (Command.DELETE.getAction().equals(input)) {
                        String key = Menu.selectPetKey(scanner);
                        petService.delete(key);
                    }

                    if (Command.SHOW.getAction().equals(input)) {
                        Menu.showPets(petService.getAll());
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}