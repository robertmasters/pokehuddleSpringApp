package com.pokehuddle.pokehuddlebackend.models;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class) //anytime something happens to an entity this listener is triggered
abstract class Auditable {


    @CreatedBy
    protected String createdby;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    protected Date createddate;

    @LastModifiedBy
    protected String lastmodifiedby;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    protected Date lastmodifieddate;


    //no getters so that the users dont see the data, only the database admins
    //if I want to send this info to the user then i would create a getter for these fields


    public String getCreatedby() {
        return createdby;
    }

    public Date getCreateddate() {
        return createddate;
    }

    public String getLastmodifiedby() {
        return lastmodifiedby;
    }

    public Date getLastmodifieddate() {
        return lastmodifieddate;
    }
}
