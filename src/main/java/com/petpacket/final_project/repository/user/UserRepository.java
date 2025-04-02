package com.petpacket.final_project.repository.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petpacket.final_project.entities.user.Role;
import com.petpacket.final_project.entities.user.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByUsername(String username);

	Optional<User> findByEmail(String email);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

	Boolean existsByUsernameAndPassword(String username, String password);
	
	List<User> findByRole(Role role);
}
