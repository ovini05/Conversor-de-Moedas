package com.example.currency_converter.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.currency_converter.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);//BUSCAR USUARIO PELO EMAIL

	Optional<User> findByUsername(String username);//BUSCAR PELO USERNAME


}
