package com.viajaperu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.viajaperu.models.Destino;
import com.viajaperu.models.Terramoza;

public interface TerramozaRepository extends JpaRepository<Terramoza, String>{

	
	@Query("SELECT MAX(t.cod_terramoza) FROM Terramoza t")
	public String ultimoId();
	
	public List<Terramoza>findByNumerodocumento(String numero_documento);
	
	@Query("SELECT t from Terramoza t where t.numerodocumento=?1 and t.cod_terramoza<>?2")
	public List<Terramoza>buscarTerramoza(String numero_documento,String cod_terramoza);
}