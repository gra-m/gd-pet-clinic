package fun.madeby.gdpetclinic.services.map;

import fun.madeby.gdpetclinic.model.Pet;
import fun.madeby.gdpetclinic.services.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import java.util.Set;

/**
 * Created by Gra_m on 2022 03 17
 *
 */

@Service
@Profile({"default", "map"})
public class PetServiceMap extends AbstractMapService<Pet> implements PetService {

}
