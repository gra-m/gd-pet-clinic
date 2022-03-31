package fun.madeby.gdpetclinic.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Gra_m on 2022 03 14
 */

public class Vet extends Person{
    private Set<Speciality> specialities = new HashSet<>();

    public Set<Speciality> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(Set<Speciality> specialities) {
        this.specialities = specialities;
    }
}
