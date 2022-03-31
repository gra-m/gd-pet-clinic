package fun.madeby.gdpetclinic.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Gra_m on 2022 03 14
 */

public class Owner extends Person{
    private String address;
    private String city;
    private String telephone;
    private Set<Pet> pets = new HashSet<>();

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Set<Pet> getPets() {
       /* if (this.pets == null) {
            return new HashSet<Pet>();
        } else */
        return pets;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }
}
