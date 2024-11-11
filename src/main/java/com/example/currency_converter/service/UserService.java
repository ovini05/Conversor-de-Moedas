package com.example.currency_converter.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.currency_converter.model.User; // Importe o seu modelo de usuário
import com.example.currency_converter.repository.UserRepository; // Importe o repositório do usuário

@Service
public class UserService {

	private final UserRepository userRepository; // Repositório para operações de banco de dados
	private final PasswordEncoder passwordEncoder; // Encoder para criptografar senhas

	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	// Método para registrar um novo usuário
	public User registerUser(String username, String password, String email) {
		// Criptografa a senha antes de armazená-la
		String encodedPassword = passwordEncoder.encode(password);

		// Cria um novo objeto User
		User newUser = new User();
		newUser.setUsername(username);
		newUser.setPassword(encodedPassword);
		newUser.setEmail(email);

		// Salva o usuário no banco de dados
		return userRepository.save(newUser);
	}

}
