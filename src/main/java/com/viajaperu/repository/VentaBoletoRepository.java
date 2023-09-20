package com.viajaperu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.viajaperu.models.VentaBoleto;

public interface VentaBoletoRepository extends JpaRepository<VentaBoleto, String>{

	@Query("SELECT MAX(v.cod_venta) FROM VentaBoleto v")
	public String ultimoCodVenta();
	
}
