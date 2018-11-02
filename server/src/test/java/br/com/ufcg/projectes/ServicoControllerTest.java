package br.com.ufcg.projectes;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.ufcg.controller.EspecialidadeController;
import br.com.ufcg.controller.ServicoController;
import br.com.ufcg.controller.UsuarioController;
import br.com.ufcg.domain.Cliente;
import br.com.ufcg.domain.Especialidade;
import br.com.ufcg.domain.Fornecedor;
import br.com.ufcg.domain.Servico;
import br.com.ufcg.domain.Usuario;
import br.com.ufcg.service.UsuarioService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServicoControllerTest {

	@Autowired
	ServicoController sc;
	
	@Autowired
	UsuarioController uc;
	
	@Autowired
	EspecialidadeController ec;
	
	@Autowired
	UsuarioService us;
	
	
	@Before
	@Transactional
	public void setUp() {
		Especialidade especialidade1 = new Especialidade("encanador");
		Especialidade especialidade2 = new Especialidade("motorista");
		Especialidade especialidade3 = new Especialidade("eletricista");
		
		List<Especialidade> listaEspecialidades1 = new ArrayList<>();
		List<Especialidade> listaEspecialidades2 = new ArrayList<>();
		
		listaEspecialidades1.add(especialidade1);
		listaEspecialidades1.add(especialidade3);
		
		listaEspecialidades2.add(especialidade1);
		listaEspecialidades2.add(especialidade2);
		listaEspecialidades2.add(especialidade3);
		
		Cliente cliente1 = new Cliente("Tiberio Gadelha M", "tiberiogadelha", "/imgs/foto.png",
				"tiberio.gomes@ccc.ufcg.edu.br", "123456789");
		Cliente cliente2 = new Cliente("Pedro Wanderley", "pedrow", "/imgs/foto.png",
				"pedro.wanderley@ccc.ufcg.edu.br", "abcdefghi");
		
		Fornecedor fornecedor1 = new Fornecedor("Carlos Alberto dos Anjos", "carlosaba", "/imgs/foto.png",
				"carlos.alberto1@gmail.com", "123456789", listaEspecialidades1);
		Fornecedor fornecedor2 = new Fornecedor("Jose Thiago Dos Santos", "jthiago", "/imgs/foto.png",
				"j.thiago@gmail.com", "123456789", listaEspecialidades2);
		
		try {
			ec.cadastraEspecialidade(especialidade1);
			ec.cadastraEspecialidade(especialidade2);
			ec.cadastraEspecialidade(especialidade3);
			
			uc.cadastrarCliente(cliente1);
			uc.cadastrarCliente(cliente2);
			
			uc.cadastrarFornecedor(fornecedor1);
			uc.cadastrarFornecedor(fornecedor2);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	@Transactional
	public void testClienteSemServico() {
	
		List<Usuario> usuarios = (List<Usuario>) uc.listaClientes().getBody().getData();
		Usuario user = null;
		for(Usuario usuario: usuarios) {
			if(usuario.getEmail().equalsIgnoreCase("pedro.wanderley@ccc.ufcg.edu.br")) {
				user = usuario;
			}
		}
		System.out.println("ANTESSSSSSSS");
		System.out.println("DEPOISSSSSSSSSSSSS");
	}
	@Test
	@Transactional
	public void testSetServicoParaFornecedor() {
		System.out.println(uc.listaClientes().getStatusCode());
		uc.listaClientes().getBody().getData();
	}
	/*
	@Test
	public void testGetServicosFromCliente() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetServicos() {
		fail("Not yet implemented");
	}

	@Test
	public void testCadastrarServico() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetServicosDisponiveisForThisFornecedor() {
		fail("Not yet implemented");
	}
	*/
}
