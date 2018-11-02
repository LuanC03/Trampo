package br.com.ufcg.projectes;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.ufcg.controller.UsuarioController;
import br.com.ufcg.domain.Cliente;
import br.com.ufcg.domain.Especialidade;
import br.com.ufcg.domain.Fornecedor;
import br.com.ufcg.domain.Usuario;
import br.com.ufcg.domain.enums.TipoUsuario;
import br.com.ufcg.domain.vo.AlterarDadosForm;
import br.com.ufcg.repository.UsuarioRepository;
import br.com.ufcg.service.EspecialidadeService;
import br.com.ufcg.service.UsuarioService;
import br.com.ufcg.util.validadores.UsuarioValidador;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsuarioControllerTest {


	@Autowired
	UsuarioController usuarioController;

	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	EspecialidadeService especialidadeService;
	
	private Usuario cliente1;
	private Usuario cliente2;
	private Usuario fornecedor1;
	private Usuario fornecedor2;
	private List<Especialidade> especialidades1;
	private List<Especialidade> especialidades2;
	private Especialidade especialidade1;
	private Especialidade especialidade2;
	private Especialidade especialidade3;
	private Especialidade especialidade4;
	
	
	
	@Before
	@Transactional
	public void setUp() {
		especialidade1 = new Especialidade("encanador");
		especialidade2 = new Especialidade("pintor");
		especialidade3 = new Especialidade("pedreiro");
		especialidade4 = new Especialidade("motorista");
		especialidades1 = new ArrayList<>();
		especialidades2 = new ArrayList<>();
		especialidades1.add(especialidade1);
		especialidades1.add(especialidade2);
		especialidades1.add(especialidade4);
		especialidades2.add(especialidade3);
		especialidades2.add(especialidade1);
		try {
			especialidadeService.criarEspecialidade(especialidade1);
			especialidadeService.criarEspecialidade(especialidade2);
			especialidadeService.criarEspecialidade(especialidade3);
			especialidadeService.criarEspecialidade(especialidade4);
		} catch(Exception e) {
			
		}
		cliente1 = new Cliente("Joao Da Silva", "joaos", "foto.png", "joao@gmail.com", "123456789");
		cliente2 = new Cliente("Paulo Cesar", "pauloc","minha.png", "paulo@gmail.com", "abcdefgh");
		fornecedor1 = new Fornecedor("Vitor Hugo", "vitor", "afoto.png", "vivi@gmail.com", "vitor123456", especialidades1);
		fornecedor2 = new Fornecedor("Caio O", "caio", "fotinha.png", "caio@gmail.com", "abc123456", especialidades2);
		
		usuarioController.cadastrarCliente((Cliente) cliente1);
		usuarioController.cadastrarCliente((Cliente) cliente2);
		usuarioController.cadastrarFornecedor((Fornecedor) fornecedor1);
		usuarioController.cadastrarFornecedor((Fornecedor) fornecedor2);
		
	}
	
	
	@Test
	@Transactional
	public void testCriarClienteValido() {
		Cliente cliente1 = new Cliente("Tiberio Gadelha M", "tiberiogadelha", "/imgs/foto.png",
				"tiberio.gomes@ccc.ufcg.edu.br", "123456789");
		try {
			usuarioController.cadastrarCliente(cliente1);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Usuario us1 = null;

		try {
			us1 = usuarioService.getByLogin(cliente1.getLogin());
		} catch (Exception e) {
			e.printStackTrace();
		}

		assertEquals("Tiberio Gadelha M", us1.getNomeCompleto());
		assertEquals("tiberiogadelha", us1.getLogin());
		assertEquals("tiberio.gomes@ccc.ufcg.edu.br", us1.getEmail());
		assertEquals(TipoUsuario.CLIENTE, us1.getTipo());

		Cliente cliente2 = new Cliente("Yuri Kelvin", "yurikelvin", "/imgs/foto.png", "yuri.kelvin@ccc.ufcg.edu.br",
				"123456789");

		try {
			usuarioController.cadastrarCliente(cliente2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@Transactional
	public void testCriarClienteInValido() {

		// Testa cliente com login de tamanho inferior a 4.
		Cliente cliente1 = new Cliente("Gustavo Victor", "gu", "/imgs/foto.png", "gustavo.victor@ccc.ufcg.edu.br",
				"123456789");
		try {
			usuarioController.cadastrarCliente(cliente1);
		} catch (Exception e) {
			assertEquals("O login deve ter no minimo 4 digitos e nao pode conter espaco", e.getMessage());
		}

		// Testa cliente com nome de tamanho inferior a 8
		Cliente cliente2 = new Cliente("Joao", "joaohenrique", "/imgs/foto.png", "joao.henrique@ccc.ufcg.edu.br",
				"123456789");
		try {
			usuarioController.cadastrarCliente(cliente2);
		} catch (Exception e) {
			assertEquals("O nome completo deve ter no minimo 8 digitos", e.getMessage());
		}

		// Testa cliente com senha de tamanho inferior a 8
		Cliente cliente3 = new Cliente("Emanuel Brito ", "emaulbrito", "/imgs/foto.png",
				"emanuel.brito@ccc.ufcg.edu.br", "1289");
		try {
			usuarioController.cadastrarCliente(cliente3);
		} catch (Exception e) {
			assertEquals("A senha deve ter no minimo 8 digitos", e.getMessage());
		}
	}

	@Test
	@Transactional
	public void testCriarClienteDuplicado() {
		// Testa cadastrar dois clientes com mesmo email e login
		Cliente cliente4 = new Cliente("Tiberio Gadelha M", "tiberiogomes", "/imgs/foto.png", "tiberio@ccc.ufcg.edu.br",
				"123456789");
		try {
			usuarioController.cadastrarCliente(cliente4);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			usuarioController.cadastrarCliente(cliente4);
		} catch (Exception e) {
			assertEquals("Email e/ou login já estão sendo usandos. Tente outros, por favor.", e.getMessage());
		}
	}

	@Test
	@Transactional
	public void testCriarFornecedorValido() {
		List<Especialidade> listaEspecialidade = new ArrayList<>();
		listaEspecialidade.add(new Especialidade("Pintor"));
		listaEspecialidade.add(new Especialidade("Encanador"));
		listaEspecialidade.add(new Especialidade("Pedreiro"));

		Fornecedor fornecedor1 = new Fornecedor("Carlos Alberto dos Anjos", "carlosaba", "/imgs/foto.png",
				"carlos.alberto1@gmail.com", "123456789", listaEspecialidade);
		try {
			usuarioController.cadastrarFornecedor(fornecedor1);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Usuario foundByLogin = null;
		try {
			foundByLogin = usuarioService.getByLogin(fornecedor1.getLogin());
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertTrue(foundByLogin.getNomeCompleto().equals(fornecedor1.getNomeCompleto()));
		assertEquals(3, ((Fornecedor) foundByLogin).getListaEspecialidades().size());
	}
	
	@Test
	@Transactional
	public void testAtualizarCamposValidos() throws Exception  {
	
		Usuario copia = new Cliente("Joao Da Silva", "joaos", "foto.png", "joao@gmail.com", "123456789");
		Usuario usuarioAntes = usuarioService.getByLogin("joaos");
		
		AlterarDadosForm form = new AlterarDadosForm("Yuri Silva", "yuri", "yuri@gmail.com", null, "afoto.png");
		
		try {
			usuarioService.atualizarDados(usuarioAntes, form);
			Usuario usuarioAtualizado = usuarioService.getByLogin("yuri");
			assertNotEquals(copia.getNomeCompleto(), usuarioAtualizado.getNomeCompleto());
			assertEquals(usuarioAntes, usuarioAtualizado);
			assertEquals(usuarioAntes.getId(), usuarioAtualizado.getId());
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	@Test
	@Transactional
	public void testAtualizarNomeInvalido() throws Exception {
	
		Usuario fornecedor = usuarioService.getByLogin(fornecedor1.getLogin());
		AlterarDadosForm form = new AlterarDadosForm("a", "vitor", "vivi@gmail.com", especialidades1, "afoto.png");
		
		try {
			usuarioService.atualizarDados(fornecedor, form);
		} catch(Exception e) {
			
			assertEquals(UsuarioValidador.TAMANHO_MINIMO_NOME_EXCEPTION, e.getMessage());
		}
		
		AlterarDadosForm form2 = new AlterarDadosForm("UM NOME MUITO GRANDE PARA ISSO DAR PROBLEMA ABCSDEFUHGFIUGFUYSGFUYWGYFGSUGFQDQIHDQ  Z", "vitor", "vivi@gmail.com", especialidades1, "afoto.png");
		
		try {
			usuarioService.atualizarDados(fornecedor, form2);
		} catch(Exception e) {
			assertEquals(UsuarioValidador.TAMANHO_MAXIMO_NOME_EXCEPTION, e.getMessage());
		}
		
	}
	
	
	@Test
	@Transactional
	public void testAtualizarEmailInvalido() throws Exception {
		Usuario cliente = usuarioService.getByLogin("pauloc");		
		
		// Formado de email invalido
		AlterarDadosForm form = new AlterarDadosForm("Paulo Cesar", "pauloc", "email.png", null, "minha.png");
		
		// Email de um usuario ja cadastrado
		AlterarDadosForm form2 = new AlterarDadosForm("Paulo Cesar", "pauloc", "caio@gmail.com", null, "minha.png");
		
		// Email vazio
		AlterarDadosForm form3 = new AlterarDadosForm("Paulo Cesar", "pauloc", "", null, "minha.png");
		
		try {
			usuarioService.atualizarDados(cliente, form);
		} catch(Exception e) {
			assertEquals(UsuarioValidador.FORMATO_EMAIL_INVALIDO, e.getMessage());
			
		}
		
		try {
			usuarioService.atualizarDados(cliente, form2);
		} catch(Exception e) {
			assertEquals("Email e/ou login já estão sendo usados. Tente outros, por favor.", e.getMessage());
			
		}
		
		try {
			usuarioService.atualizarDados(cliente, form3);
		} catch(Exception e) {
			assertEquals(UsuarioValidador.FORMATO_EMAIL_INVALIDO, e.getMessage());
		}
		
		
	}
}
