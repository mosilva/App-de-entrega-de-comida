package br.com.marcelo.bluefood.domain.restaurante;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RestauranteRepository extends JpaRepository<Restaurante, Integer>
/*<tipo do objeto, tipo do id> */
{
	
	public Restaurante findByEmail(String email); /*ja vai funcionar, o Spring Data reconhece o NOME do método e entende
	que é pra buscar cliente pelo atributo email, cria a query automaticamente, não precisa fazer nada. 
	Nessa caso é uma interface não precisa implementar nada, mas ele busca uma querie padrão*/

	public List<Restaurante> findByNomeIgnoreCaseContaining(String nome);
	
	public List<Restaurante> findByCategorias_Id(Integer categoriaId);
	
	
}
