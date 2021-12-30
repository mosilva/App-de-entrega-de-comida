package br.com.marcelo.bluefood.domain.cliente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>
/*<tipo do objeto, tipo do id> */
{
	
	public Cliente findByEmail(String email); /*ja vai funcionar, o Spring Data reconhece o NOME do metodo e entende
	que � pra buscar cliente pelo atributo email, cria a query automaticamente, n�o precisa fazer nada. 
	Nessa caso � uma interface n�o precisa implementar nada, mas ele busca uma querie padr�o*/

}
