package com.randazzo.tep.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "users", 
    uniqueConstraints = { 
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email") 
})
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 20)
	private String username;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	@NotBlank
	@Size(max = 120)
	private String password;
	
	@NotNull
    @Enumerated(EnumType.STRING)
	private ERole role;

	@NotNull
    @Enumerated(EnumType.STRING)
	private EClass classe;

	@NotNull
    @Enumerated(EnumType.STRING)
	private ESection section;

	public User() {}

	public User(Long id, String username, String email, String password, ERole role, EClass classe, ESection section) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.role = role;
		this.classe = classe;
		this.section = section;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ERole getRole() {
		return this.role;
	}

	public void setRole(ERole role) {
		this.role = role;
	}

	public EClass getClasse() {
		return this.classe;
	}

	public void setClasse(EClass classe) {
		this.classe = classe;
	}

	public ESection getSection() {
		return this.section;
	}

	public void setSection(ESection section) {
		this.section = section;
	}
}