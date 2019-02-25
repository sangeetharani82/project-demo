package org.launchcode.SpringFilterBasedAuth.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@MappedSuperclass
public abstract class AbstractEntity {
    @Id
    @GeneratedValue
    private int uid;

    public int getUid() {
        return this.uid;
    }

}
