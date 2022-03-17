package fun.madeby.gdpetclinic.model;

import java.io.Serializable;

/**
 * Created by Gra_m on 2022 03 17
 * This is just so much tidier than all that practice adding id to every model..
 */

public class BaseEntity implements Serializable {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
