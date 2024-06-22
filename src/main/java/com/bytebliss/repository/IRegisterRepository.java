package com.bytebliss.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bytebliss.entity.Register;
@Repository
public interface IRegisterRepository extends JpaRepository<Register , Long>{
	
	public boolean existsByEmail(String email);

//	public Optional<Register> findByFullName(String username);

	public Optional<Register> findByEmail(String username);


}
