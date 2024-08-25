package com.petpacket.final_project.entities.service;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "\"ServicePetType\"", schema = "public")
@IdClass(ServicePetTypeId.class)
public class ServicePetType {

    @Id
    @Column(name = "service_id")
    private Integer serviceId;

    @Id
    @Column(name = "pet_type_id")
    private Integer petTypeId;

    @Column(name = "nothing")
    private String nothing;

    public ServicePetType() {
    }

    public ServicePetType(Integer serviceId, Integer petTypeId, String nothing) {
        this.serviceId = serviceId;
        this.petTypeId = petTypeId;
        this.nothing = nothing;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getPetTypeId() {
        return petTypeId;
    }

    public void setPetTypeId(Integer petTypeId) {
        this.petTypeId = petTypeId;
    }

    public String getNothing() {
        return nothing;
    }

    public void setNothing(String nothing) {
        this.nothing = nothing;
    }
}
