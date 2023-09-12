package com.viajaperu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.viajaperu.models.Conductor;

public interface ConductorRepository extends JpaRepository<Conductor, String> {

	@Query("SELECT MAX(b.cod_conductor) FROM Conductor b")
	public String ultimoId();
	
	List<Conductor> findByNrolicencia(String licencia);
	
	
	@Query("select b from Conductor b where b.nrolicencia=?1 and b.cod_conductor<>?2")
	List<Conductor> buscarPorLicenciaDiferenteCodigo(String licencia, String codigo);
	
}