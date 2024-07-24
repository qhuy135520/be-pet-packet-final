package com.petpacket.final_project.entities;

import java.io.Serializable;
import java.util.Objects;

public class ServicePetTypeId implements Serializable {

    private Integer serviceId;
    private Integer petTypeId;

    public ServicePetTypeId() {
    }

    public ServicePetTypeId(Integer serviceId, Integer petTypeId) {
        this.serviceId = serviceId;
        this.petTypeId = petTypeId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServicePetTypeId that = (ServicePetTypeId) o;
        return Objects.equals(serviceId, that.serviceId) &&
               Objects.equals(petTypeId, that.petTypeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceId, petTypeId);
    }
}
