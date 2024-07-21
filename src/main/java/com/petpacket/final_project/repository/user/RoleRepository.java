package com.petpacket.final_project.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petpacket.final_project.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
