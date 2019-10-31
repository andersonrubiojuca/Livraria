package br.com.livraria.livraria.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.livraria.livraria.model.Compras;
import br.com.livraria.livraria.repository.ComprasRepository;

@Service
public class ComprasService {
	
	@Autowired
	private ComprasRepository repository;
	
	public void salvar (Compras compras) {
		repository.save(compras);
	}
	
	public void remover(Integer id) {
		repository.deleteById(id);
	}
	
	public Optional<Compras> listarPorId(Integer id){
		Optional<Compras> OpCompras = repository.findById(id);
		return OpCompras;
	}
	
	public ArrayList<Compras> listar(){
		Iterable<Compras> compras = repository.findAll();
		ArrayList<Compras> listaCompras = new ArrayList<>();
		
		for(Compras compra : compras) {
			listaCompras.add(compra);
		}
		
		return listaCompras;
	}

}
