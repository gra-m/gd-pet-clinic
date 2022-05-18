package fun.madeby.gdpetclinic.services.map;

import fun.madeby.gdpetclinic.model.Speciality;
import fun.madeby.gdpetclinic.model.Vet;
import fun.madeby.gdpetclinic.services.SpecialitiesService;
import fun.madeby.gdpetclinic.services.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Gra_m on 2022 03 17
 */

@Service
@Profile({"default", "map"})
public class VetServiceMap extends AbstractMapService<Vet> implements VetService {
    private final SpecialitiesService specialityService;

    public VetServiceMap(SpecialitiesService speciality_service) {
        specialityService = speciality_service;
    }

    @Override
    public Vet save(Vet obj) {
       if (obj == null)
           return null;

       HashSet<Speciality> specialities = (HashSet<Speciality>) obj.getSpecialities();
        if (specialities.isEmpty())
            return super.save(obj);

        boolean specialitiesPersisted = checkSpecialities(specialities);
        if (specialitiesPersisted)
            return super.save(obj);
        else
            return null;
    }

    /**
     * Aim: To emulate Hibernate by ensuring all object IDs are in sync.
     * Specialities must not be null and must have an id when a vet  is saved.
     * A vet may not have a speciality when saved.
     * @param specialities
     * @return boolean
     */
    private boolean checkSpecialities(HashSet<Speciality> specialities) {
        if(specialities.isEmpty())
            return false;
        specialities.forEach(spec -> {
            Long specId = spec.getId();
            if (specId == null) {
                Speciality savedSpeciality = specialityService.save(spec);
                spec.setId(savedSpeciality.getId());
            }
        });
        return true;
    }


    @Override
    public Vet findByVetByLastName(String lastName) {
        return null;
    }
}
