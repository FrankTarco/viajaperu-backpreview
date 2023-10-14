package com.viajaperu.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="tb_pago")
@Getter
@Setter
public class Pago {

	@Id
	private String cod_pago;											
	private String numero_tarjeta;			
	private String fecha_operacion;

	
	@ManyToOne
	@JoinColumn(name="cod_venta")
	private VentaBoleto venta;
	
	@ManyToOne
	@JoinColumn(name="cod_cliente")
	private Cliente cliente;
}
