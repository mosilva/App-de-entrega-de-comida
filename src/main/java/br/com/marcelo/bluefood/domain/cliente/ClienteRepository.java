package br.com.marcelo.bluefood.domain.cliente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>
/*<tipo do objeto, tipo do id> */
{
	
	public Cliente findByEmail(String email); /*ja vai funcionar, o Spring Data reconhece o NOME do método e entende
	que é pra buscar cliente pelo atributo email, cria a query automaticamente, não precisa fazer nada. 
	Nessa caso é uma interface não precisa implementar nada, mas ele busca uma querie padrão*/

}
