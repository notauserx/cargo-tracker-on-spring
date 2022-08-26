package com.practicalddd.cargotracker.bookingservice.domain.model.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
/**
 * Location Entity class represented by a unique 5-digit UN Location code.
 */
@Embeddable
public class Location {
    @Column(name = "origin_id", insertable = false, updatable = false)
    private String unLocCode;
    public Location(){}
    public Location(String unLocCode){this.unLocCode = unLocCode;}
    public void setUnLocCode(String unLocCode){this.unLocCode = unLocCode;}
    public String getUnLocCode(){return this.unLocCode;}
}
