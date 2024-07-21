package com.petpacket.final_project.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "\"ServicePetType\"", schema = "public")
@IdClass(ServicePetTypeId.class)
public class ServicePetType {

    @Id
    private Integer serviceId;

    @Id
    private Integer petTypeId;

    private String nothing;

    // Constructor mặc định
    public ServicePetType() {
    }

    // Constructor với tham số
    public ServicePetType(Integer serviceId, Integer petTypeId, String nothing) {
        this.serviceId = serviceId;
        this.petTypeId = petTypeId;
        this.nothing = nothing;
    }

    // Getter và Setter
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
