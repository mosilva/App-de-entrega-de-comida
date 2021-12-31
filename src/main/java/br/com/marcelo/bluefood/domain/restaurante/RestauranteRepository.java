package br.com.marcelo.bluefood.domain.restaurante;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RestauranteRepository extends JpaRepository<Restaurante, Integer>
/*<tipo do objeto, tipo do id> */
{
	
	public Restaurante findByEmail(String email); 

	public List<Restaurante> findByNomeIgnoreCaseContaining(String nome);
	
	public List<Restaurante> findByCategorias_Id(Integer categoriaId);
	
	
}
