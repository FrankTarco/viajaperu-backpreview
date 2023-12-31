package com.viajaperu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.viajaperu.models.Boleto;
import com.viajaperu.models.Cliente;
import com.viajaperu.models.Pago;
import com.viajaperu.models.Pasajero;
import com.viajaperu.models.VentaBoleto;

@Service
public class TransaccionServiceImp implements TransaccionService{

	@Autowired
	private VentaBoletoService ventaBolRepo;
	
	@Autowired
	private BoletoService boletoRepo;
	
	@Autowired
	private PasajeroService pasajeroRepo;
	
	@Autowired
	private ClienteService clienteRepo;
	
	@Autowired
	private PagoService pagoRepo;
	
	@Override
	public Pago registrarPago(Pago objPago, VentaBoleto objVenta, List<Pasajero>objPasajeros, Cliente objCliente) {	
		return null;
	}

	@Override
	@Transactional
	public Pago registrarPago2(VentaBoleto objVenta, List<Pasajero> lstPasajeros, List<Boleto> lstBoletos,
			Cliente objCliente, Pago objPago) {
		
		int[] contador = {0};
		//REGISTRAR PRIMERO LA VENTA
		ventaBolRepo.registarVentaBoleto(objVenta);
		
		lstPasajeros.forEach(pasajero -> {
			
			List<Pasajero>pasajero_existe = pasajeroRepo.buscarPasajeroXDocumento(pasajero.getNumeroDocumento());
			
			if(CollectionUtils.isEmpty(pasajero_existe)) {
				pasajeroRepo.registrarActualizar(pasajero);
				
				for (int i = contador[0]; i < lstBoletos.size();) {
					Boleto been =  lstBoletos.get(i);
					been.setPasajero(pasajero);
					been.setVenta(objVenta);
					boletoRepo.registrar(been);
					contador[0]++;
					break;
				}
			}
			else {
				for (int i = contador[0]; i < lstBoletos.size();) {
					Boleto been =  lstBoletos.get(i);
					been.setPasajero(pasajero_existe.get(0));
					been.setVenta(objVenta);
					boletoRepo.registrar(been);
					contador[0]++;
					break;
				}
			}		
		});
			
		//VALIDAR SI EL CLIENTE EXISTE
		Cliente cli_existe = clienteRepo.clientPorDocument(objCliente.getNumeroDocumento());
		if(cli_existe == null) {
			clienteRepo.registrar(objCliente);
			objPago.setCliente(objCliente);
		}
		else {
			objPago.setCliente(cli_existe);
		}
				
		objPago.setVenta(objVenta);
			
		return pagoRepo.registrarPago(objPago);
	}

}
