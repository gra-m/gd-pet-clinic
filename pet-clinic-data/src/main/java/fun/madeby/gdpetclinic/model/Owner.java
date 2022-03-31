package fun.madeby.gdpetclinic.model;

import java.util.Set;

/**
 * Created by Gra_m on 2022 03 14
 */

public class Owner extends Person{
    private Set<Pet> pets;

    public Set<Pet> getPets() {
        return pets;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }
}
