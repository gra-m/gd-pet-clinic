package fun.madeby.gdpetclinic.bootstrap;

import fun.madeby.gdpetclinic.model.*;
import fun.madeby.gdpetclinic.services.*;
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
    private final SpecialitiesService SPECIALITY_SERVICE;
    private final VisitService VISIT_SERVICE;

    public DataLoader(OwnerService ownerService,
                      VetService vetService,
                      PetTypeService petTypeService,
                      SpecialitiesService specialitiesService,
                      VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.PET_TYPE_SERVICE = petTypeService;
        this.SPECIALITY_SERVICE = specialitiesService;
        this.VISIT_SERVICE = visitService;
    }

    @Override
    public void run(String... args) throws Exception {
        int count = vetService.findAll().size();
        if (count == 0)
        loadData();
    }

    private void loadData() {
        //Testing builder, it currently does not work with inheritance:
        Owner.builder().address("asdf").build();
        // builder cannot access firstname/lastname/id of super classes.


        Speciality speciality01 = new Speciality();
        Speciality speciality02 = new Speciality();
        Speciality speciality03 = new Speciality();
        speciality01.setDescription("Radiology");
        speciality02.setDescription("Surgery");
        speciality03.setDescription("Dentistry");
        Speciality savedRadiology = SPECIALITY_SERVICE.save(speciality01);
        Speciality savedSurgery = SPECIALITY_SERVICE.save(speciality02);
        Speciality savedDentistry = SPECIALITY_SERVICE.save(speciality03);

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
        Visit pet1Visit = new Visit();
        pet1Visit.setPet(owner2Pet1);
        pet1Visit.setDate(LocalDate.now());
        pet1Visit.setDescription("Ate too much pie");
        VISIT_SERVICE.save(pet1Visit);
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
        vet1.getSpecialities().add(savedRadiology);
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Hashasash");
        vet2.setLastName("Vhshwas");
        vet2.getSpecialities().add(savedSurgery);
        vetService.save(vet2);
        System.out.println("Loaded Vets....");

        System.out.println("PRINTING VET SPECIALITIES");
        vet1.getSpecialities().forEach(spec -> {
                    System.out.println(spec.toString());
                });
        vet2.getSpecialities().forEach(spec -> {
            System.out.println(spec.toString());
        });
    }
}
