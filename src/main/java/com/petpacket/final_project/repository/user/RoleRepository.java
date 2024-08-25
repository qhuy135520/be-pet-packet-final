package com.petpacket.final_project.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petpacket.final_project.entities.user.ERole;
import com.petpacket.final_project.entities.user.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
	 Optional<Role> findByRoleName(ERole roleName);
}
