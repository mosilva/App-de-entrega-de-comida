package br.com.marcelo.bluefood.application.service;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.marcelo.bluefood.domain.cliente.Cliente;
import br.com.marcelo.bluefood.domain.cliente.ClienteRepository;
import br.com.marcelo.bluefood.domain.restaurante.ItemCardapio;
import br.com.marcelo.bluefood.domain.restaurante.ItemCardapioRepository;
import br.com.marcelo.bluefood.domain.restaurante.Restaurante;
import br.com.marcelo.bluefood.domain.restaurante.RestauranteComparator;
import br.com.marcelo.bluefood.domain.restaurante.RestauranteRepository ;
import br.com.marcelo.bluefood.domain.restaurante.SearchFilter;
import br.com.marcelo.bluefood.domain.restaurante.SearchFilter.SearchType;
import br.com.marcelo.bluefood.util.SecurityUtils;


@Service
public class RestauranteService {

	@Autowired
	private RestauranteRepository RestauranteRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ItemCardapioRepository itemCardapioRepository;
	
	@Autowired
	private ImageService imageService;
	
	@org.springframework.transaction.annotation.Transactional
	public void saveRestaurante(Restaurante restaurante) throws ValidationException {
		
		if(!validateEmail(restaurante.getEmail(), restaurante.getId())) {
			throw new ValidationException("O email está duplicado");
		}
		
		if(restaurante.getId() != null) {
			Restaurante restauranteDB = RestauranteRepository.findById(restaurante.getId()).orElseThrow(NoSuchElementException::new);
			restaurante.setSenha(restauranteDB.getSenha());	
			restaurante.setLogotipo(restauranteDB.getLogotipo());
			RestauranteRepository.save(restaurante);
			
		} else {
			restaurante.encryptPassword();
			restaurante = RestauranteRepository.save(restaurante);
			
			restaurante.setLogotipoFileName();
			imageService.uploadLogotipo(restaurante.getLogotipoFile(), restaurante.getLogotipo());						
		}
		
		
		
		
	}
	
	private boolean validateEmail(String email, Integer id) {
		
		Cliente cliente = clienteRepository.findByEmail(email);
		
		if(cliente != null) {
			return false;
		}

		
		Restaurante restaurante = RestauranteRepository.findByEmail(email);
		
		if (restaurante != null) {
			if (id== null) {
				return false;
			} 
			
			if(!restaurante.getId().equals(id)) {
				return false;
			}			
		}
		
		return true;
		
	}
	
	/*Metodo para retornar os restaurantes*/
	
	public List<Restaurante> search(SearchFilter filter) {
		
		List<Restaurante> restaurantes;
		
		if(filter.getSearchType() == SearchType.Texto) {
			restaurantes = RestauranteRepository.findByNomeIgnoreCaseContaining(filter.getTexto());
		} else if (filter.getSearchType() == SearchType.Categoria) {
			
			restaurantes = RestauranteRepository.findByCategorias_Id(filter.getCategoriaId());
						
		} else {
			throw new IllegalStateException("O tipo de busca " + filter.getSearchType() + " não é suportado");
		}
		
		Iterator<Restaurante> it = restaurantes.iterator();
		
		while (it.hasNext()) {
			Restaurante restaurante = it.next();
			double taxaEntrega = restaurante.getTaxaEntrega() .doubleValue();
			
			if (filter.isEntregaGratis() && taxaEntrega > 0
				|| !filter.isEntregaGratis() && taxaEntrega == 0) {				
				it.remove();
					}
			
			}
			
			RestauranteComparator comparator = new RestauranteComparator(filter, SecurityUtils.loggedCliente().getCep());
			
			restaurantes.sort(comparator);	
			
			return restaurantes;
				
		}
		
		@Transactional
		public void saveItemCardapio(ItemCardapio itemCardapio) {
			
			itemCardapio = itemCardapioRepository.save(itemCardapio);	
			itemCardapio.setImagemFileName();
			imageService.uploadComida(itemCardapio.getImagemFile(), itemCardapio.getImagem());
		}
		
		
	
	
	
	
}
