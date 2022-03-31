package fun.madeby.gdpetclinic.bootstrap;

import fun.madeby.gdpetclinic.model.Owner;
import fun.madeby.gdpetclinic.model.Pet;
import fun.madeby.gdpetclinic.model.PetType;
import fun.madeby.gdpetclinic.model.Vet;
import fun.madeby.gdpetclinic.services.OwnerService;
import fun.madeby.gdpetclinic.services.PetService;
import fun.madeby.gdpetclinic.services.PetTypeService;
import fun.madeby.gdpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Created by Gra_m on 2022 03 19
 */

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService PET_TYPE_SERVICE;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.PET_TYPE_SERVICE = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType petType1 = new PetType();
        petType1.setName("Pet Type One");
        PetType petType2 = new PetType();
        petType2.setName("Pet Type Two");
        PetType petType3 = new PetType();
        petType3.setName("Pet Type Three");

        PetType savedPetType1 = PET_TYPE_SERVICE.save(petType1);
        PetType savedPetType2 = PET_TYPE_SERVICE.save(petType2);
        PetType savedPetType3 = PET_TYPE_SERVICE.save(petType3);

        System.out.println("Loaded PetTypes...");




        Owner owner1 = new Owner();
        owner1.setFirstName("Hilbert");
        owner1.setLastName("Humberdick");
        owner1.setAddress("34, Langsdown Way");
        owner1.setCity("Ardover");
        owner1.setTelephone("+23 0515 634561");

        Pet owner1Pet1 = new Pet();
        Pet owner1Pet2 = new Pet();
        owner1Pet1.setName("Dinko");
        owner1Pet2.setName("Spag");
        owner1Pet1.setPetType(savedPetType1);
        owner1Pet2.setPetType(savedPetType3);
        owner1Pet1.setBirthDate(LocalDate.now());
        owner1Pet2.setBirthDate(LocalDate.now());
        owner1Pet1.setOwner(owner1);
        owner1Pet2.setOwner(owner1);
        owner1.getPets().add(owner1Pet1);
        owner1.getPets().add(owner1Pet2);
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Halsow");
        owner2.setLastName("Lalsew");
        owner2.setAddress("139, Denton Place");
        owner2.setCity("Lef-tover");
        owner2.setTelephone("+03 0617 635591");

        Pet owner2Pet1 = new Pet();
        owner2Pet1.setName("Schmeeg-Heeeeed");
        owner2Pet1.setPetType(savedPetType2);
        owner2Pet1.setBirthDate(LocalDate.now());
        owner2Pet1.setOwner(owner2);
        owner2.getPets().add(owner2Pet1);
        ownerService.save(owner2);

        System.out.println("Loaded Owners with their pets....");

        System.out.println("PRINTING OWNER PETS");

        owner1.getPets().forEach(pet -> {
            System.out.println(pet);
        });

        owner2.getPets().forEach(pet -> {
            System.out.println(pet);
        });




        Vet vet1 = new Vet();
        vet1.setFirstName("Vvsiw");
        vet1.setLastName("VHshwas");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Hashasash");
        vet2.setLastName("Vhshwas");

        vetService.save(vet2);

        System.out.println("Loaded Vets....");

    }
}
