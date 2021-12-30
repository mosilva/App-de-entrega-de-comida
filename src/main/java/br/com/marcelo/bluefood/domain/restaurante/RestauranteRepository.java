package br.com.marcelo.bluefood.domain.restaurante;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RestauranteRepository extends JpaRepository<Restaurante, Integer>
/*<tipo do objeto, tipo do id> */
{
	
	public Restaurante findByEmail(String email); /*ja vai funcionar, o Spring Data reconhece o NOME do m�todo e entende
	que � pra buscar cliente pelo atributo email, cria a query automaticamente, n�o precisa fazer nada. 
	Nessa caso � uma interface n�o precisa implementar nada, mas ele busca uma querie padr�o*/

	public List<Restaurante> findByNomeIgnoreCaseContaining(String nome);
	
	public List<Restaurante> findByCategorias_Id(Integer categoriaId);
	
	
}
