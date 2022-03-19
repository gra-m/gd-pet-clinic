package fun.madeby.gdpetclinic.bootstrap;

import fun.madeby.gdpetclinic.model.Owner;
import fun.madeby.gdpetclinic.model.Vet;
import fun.madeby.gdpetclinic.services.OwnerService;
import fun.madeby.gdpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by Gra_m on 2022 03 19
 */

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {
        Owner owner1 = new Owner();
        owner1.setFirstName("Hilbert");
        owner1.setLastName("Humberdick");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Halsow");
        owner2.setLastName("Lalsew");

        ownerService.save(owner2);

        System.out.println("Loaded Owners....");

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
