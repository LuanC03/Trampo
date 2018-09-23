package br.com.ufcg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ufcg.models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,String> {

	Usuario findByLoginAndSenha(String login,String senha);
}
