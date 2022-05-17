package fun.madeby.gdpetclinic.services.map;

import fun.madeby.gdpetclinic.model.Speciality;
import fun.madeby.gdpetclinic.services.SpecialitiesService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by Gra_m on 2022 03 31
 */
@Service
@Profile({"default", "map"})
public class SpecialitiesServiceMap extends AbstractMapService<Speciality> implements SpecialitiesService {

}
