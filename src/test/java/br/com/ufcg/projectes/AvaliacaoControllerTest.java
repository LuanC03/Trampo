package br.com.ufcg.projectes;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.ufcg.controller.EspecialidadeController;
import br.com.ufcg.controller.ServicoController;
import br.com.ufcg.controller.UsuarioController;
import br.com.ufcg.domain.Avaliacao;
import br.com.ufcg.domain.Cliente;
import br.com.ufcg.domain.Endereco;
import br.com.ufcg.domain.Especialidade;
import br.com.ufcg.domain.Fornecedor;
import br.com.ufcg.domain.Servico;
import br.com.ufcg.domain.Usuario;
import br.com.ufcg.dto.AvaliacaoDTO;
import br.com.ufcg.repository.AvaliacaoRepository;
import br.com.ufcg.repository.EspecialidadeRepository;
import br.com.ufcg.repository.ServicoRepository;
import br.com.ufcg.repository.UsuarioRepository;
import br.com.ufcg.service.AvaliacaoService;
import br.com.ufcg.service.ServicoService;
import br.com.ufcg.service.UsuarioService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AvaliacaoControllerTest {

	@Autowired
	AvaliacaoService avaliacaoService;
	
	@Autowired
	ServicoController sc;
	
	@Autowired
	ServicoService ss;
	
	@Autowired
	UsuarioController uc;
	
	@Autowired
	EspecialidadeController ec;
	
	@Autowired
	UsuarioService us;
	
	@Autowired
	EspecialidadeRepository er;
	
	@Autowired
	UsuarioRepository ur;
	
	@Autowired
	ServicoRepository sr;
	
	@Autowired
	AvaliacaoRepository ar;
	

	private Servico servico1;
	private Servico servico2;
	private Usuario cliente1;
	private Usuario cliente2;
	private Usuario fornecedor1;
	private Usuario fornecedor2;
	
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
		
		cliente1 = new Cliente("Tiberio Gadelha M", "tiberiogadelha", "/imgs/foto.png",
				"tiberio.gomes@ccc.ufcg.edu.br", "123456789");
		cliente2 = new Cliente("Pedro Wanderley", "pedrow", "/imgs/foto.png",
				"pedro.wanderley@ccc.ufcg.edu.br", "abcdefghi");
		
		fornecedor1 = new Fornecedor("Carlos Alberto dos Anjos", "carlos", "/imgs/foto.png",
				"carlos.alberto1@gmail.com", "123456789", listaEspecialidades1);
		fornecedor2 = new Fornecedor("Jose Thiago Santos", "thiago", "/imgs/foto.png",
				"j.thiago@gmail.com", "123456789", listaEspecialidades2);
		
		try {
			ec.cadastraEspecialidade(especialidade1);
			ec.cadastraEspecialidade(especialidade2);
			ec.cadastraEspecialidade(especialidade3);
			
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		uc.cadastrarCliente((Cliente) cliente1);
		uc.cadastrarCliente((Cliente) cliente2);
		
		uc.cadastrarFornecedor((Fornecedor) fornecedor1);
		uc.cadastrarFornecedor((Fornecedor) fornecedor2);
		
		
		
		Endereco endereco1 = new Endereco();
		endereco1.setBairro("liberdade");
		endereco1.setRua("rua souza e lima");
		endereco1.setNumero("129");
		
		Endereco endereco2 = new Endereco();
		endereco2.setBairro("catole");
		endereco2.setNumero("456");
		endereco2.setRua("rua francisco");
		
		LocalDate data1 = LocalDate.now().plusDays(40);
		LocalDate data2 = LocalDate.now().plusDays(5);
		
		LocalTime time1 = LocalTime.of(15, 0);
		LocalTime time2 = LocalTime.of(12,30);
		
		BigDecimal valor1 = BigDecimal.valueOf(25.99);
		BigDecimal valor2 = BigDecimal.valueOf(359);
		
		servico1 = new Servico("encanador", "pia quebrada", data1, time1, valor1, endereco1);
		servico2 = new Servico("motorista", "uma viagem", data2, time2, valor2, endereco2);
	}
	
	@After
	@Transactional
	public void limpeza() {
		sr.deleteAll();
		ur.deleteAll();
		er.deleteAll();
		ar.deleteAll();
	}
	
	
	
	@Test
	@Transactional
	public void testAdicionarAvaliacaoValida() throws Exception {
		Cliente cliente = (Cliente) us.getByLogin(cliente1.getLogin());
		Fornecedor fornecedor = (Fornecedor) us.getByLogin(fornecedor1.getLogin());
		
		Servico servicoCadastrado = ss.criarServico(cliente, servico1);
		Servico servicoAceito = ss.setServicoParaFornecedor(servicoCadastrado, fornecedor);
		Servico servicoConcluido = ss.concluirServico(servicoAceito, (Fornecedor) fornecedor);
		
		Avaliacao avaliacaoClienteParaFornecedor = new Avaliacao(3.5);
		Avaliacao avaliacaoFornecedorParaCliente = new Avaliacao(2.0);
		AvaliacaoDTO avaliacaoParaClienteDTO = new AvaliacaoDTO(avaliacaoFornecedorParaCliente, servicoConcluido);
		AvaliacaoDTO avaliacaoParaFornecedorDTO = new AvaliacaoDTO(avaliacaoClienteParaFornecedor, servicoConcluido);
		
		assertFalse(servicoConcluido.isClienteAvaliou());
		assertFalse(servicoConcluido.isFornecedorAvaliou());
		
		// o cliente avalia o fornecedor
		try {
			avaliacaoService.avaliarUsuario(cliente, avaliacaoParaFornecedorDTO);
			List<Avaliacao> avaliacoesDoFornecedor = fornecedor.getAvaliacoes();
			assertTrue(avaliacoesDoFornecedor.get(0).getNota().equals(avaliacaoClienteParaFornecedor.getNota()));
			assertEquals(3.5, avaliacaoService.calcularAvaliacaoMedia(fornecedor), 0.00000001);
			assertTrue(servicoConcluido.isClienteAvaliou());
			assertFalse(servicoConcluido.isFornecedorAvaliou());
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		// o fornecedor avalia o cliente
		try {
			avaliacaoService.avaliarUsuario(fornecedor, avaliacaoParaClienteDTO);
			List<Avaliacao> avaliacoesDoCliente = cliente.getAvaliacoes();
			assertTrue(avaliacoesDoCliente.get(0).getNota().equals(avaliacaoFornecedorParaCliente.getNota()));
			assertEquals(2.0, avaliacaoService.calcularAvaliacaoMedia(cliente), 0.00000001);
			assertTrue(servicoConcluido.isClienteAvaliou());
			assertTrue(servicoConcluido.isFornecedorAvaliou());
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	@Transactional
	public void testAvaliarComNotaInvalida() throws Exception {
		Cliente cliente = (Cliente) us.getByLogin(cliente2.getLogin());
		Fornecedor fornecedor = (Fornecedor) us.getByLogin(fornecedor2.getLogin());
		
		Servico servicoCadastrado = ss.criarServico(cliente, servico2);
		Servico servicoAceito = ss.setServicoParaFornecedor(servicoCadastrado, fornecedor);
		Servico servicoConcluido = ss.concluirServico(servicoAceito, (Fornecedor) fornecedor);
		
		Avaliacao avaliacaoClienteParaFornecedor = new Avaliacao(20.0);
		Avaliacao avaliacaoFornecedorParaCliente = new Avaliacao(-7.0);
		AvaliacaoDTO avaliacaoParaClienteDTO = new AvaliacaoDTO(avaliacaoFornecedorParaCliente, servicoConcluido);
		AvaliacaoDTO avaliacaoParaFornecedorDTO = new AvaliacaoDTO(avaliacaoClienteParaFornecedor, servicoConcluido);
		
		assertFalse(servicoConcluido.isClienteAvaliou());
		assertFalse(servicoConcluido.isFornecedorAvaliou());
		
		// O cliente tenta avaliar com nota muito alta
		try {
			avaliacaoService.avaliarUsuario(cliente, avaliacaoParaFornecedorDTO);
		} catch(Exception e) {
			assertEquals("A nota tem que ser entre 0 e 5!", e.getMessage());
		}
		
		// O fornecedor tenta avaliar com nota negativa
		try {
			avaliacaoService.avaliarUsuario(fornecedor, avaliacaoParaClienteDTO);
		} catch(Exception e) {
			assertEquals("A nota tem que ser entre 0 e 5!", e.getMessage());
		}
	}
	
	@Test
	@Transactional
	public void testAvaliarServicoNaoConcluido() throws Exception {
		Cliente cliente = (Cliente) us.getByLogin(cliente1.getLogin());
		Fornecedor fornecedor = (Fornecedor) us.getByLogin(fornecedor2.getLogin());
		
		Servico servicoCadastrado = ss.criarServico(cliente, servico1);
		Servico servicoAceito = ss.setServicoParaFornecedor(servicoCadastrado, fornecedor);
		
		assertFalse(servicoAceito.isClienteAvaliou());
		assertFalse(servicoAceito.isFornecedorAvaliou());
		
		Avaliacao avaliacaoClienteParaFornecedor = new Avaliacao(5.0);
		AvaliacaoDTO avaliacaoParaFornecedorDTO = new AvaliacaoDTO(avaliacaoClienteParaFornecedor, servicoAceito);
		
		// O servico ainda nao foi concluido e algum usuario tenta avaliar
		try {
			avaliacaoService.avaliarUsuario(cliente, avaliacaoParaFornecedorDTO);
		} catch(Exception e) {
			assertEquals("Você só pode avaliar o usuário, se o serviço estiver concluído!", e.getMessage());
		}
	}
	
	@Test
	@Transactional
	public void testAvaliarServicoInvalido() throws Exception {
		Fornecedor fornecedor = (Fornecedor) us.getByLogin(fornecedor1.getLogin());
		servico2.setId((long) 2812);
		Avaliacao avaliacaoFornecedorParaCliente = new Avaliacao(2.4);
		AvaliacaoDTO avaliacaoParaClienteDTO = new AvaliacaoDTO(avaliacaoFornecedorParaCliente, servico2);
		
		// O usuario tenta avaliar por um servico que nao existe
		try {
			avaliacaoService.avaliarUsuario(fornecedor, avaliacaoParaClienteDTO);
		} catch(Exception e) {
			assertEquals("Serviço não cadastrado no banco de dados.", e.getMessage());
		}
		
	}
	
	@Test
	@Transactional
	public void testAvaliarServicoMaisDeUmaVez() throws Exception {
		Cliente cliente = (Cliente) us.getByLogin(cliente1.getLogin());
		Fornecedor fornecedor = (Fornecedor) us.getByLogin(fornecedor1.getLogin());
		
		Servico servicoCadastrado = ss.criarServico(cliente, servico1);
		Servico servicoAceito = ss.setServicoParaFornecedor(servicoCadastrado, fornecedor);
		Servico servicoConcluido = ss.concluirServico(servicoAceito, (Fornecedor) fornecedor);
		
		Avaliacao avaliacaoClienteParaFornecedor = new Avaliacao(3.5);
		AvaliacaoDTO avaliacaoParaFornecedorDTO = new AvaliacaoDTO(avaliacaoClienteParaFornecedor, servicoConcluido);
		
		assertFalse(servicoConcluido.isClienteAvaliou());
		assertFalse(servicoConcluido.isFornecedorAvaliou());
		
		// o cliente avalia o fornecedor
		try {
			avaliacaoService.avaliarUsuario(cliente, avaliacaoParaFornecedorDTO);
			List<Avaliacao> avaliacoesDoFornecedor = fornecedor.getAvaliacoes();
			assertTrue(avaliacoesDoFornecedor.get(0).getNota().equals(avaliacaoClienteParaFornecedor.getNota()));
			assertEquals(3.5, avaliacaoService.calcularAvaliacaoMedia(fornecedor), 0.00000001);
			assertTrue(servicoConcluido.isClienteAvaliou());
			assertFalse(servicoConcluido.isFornecedorAvaliou());
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		// O cliente tenta avaliar o mesmo servico
		try {
			avaliacaoService.avaliarUsuario(cliente, avaliacaoParaFornecedorDTO);
		} catch(Exception e) {
			assertEquals("Você já avaliou o usuário por esse serviço!", e.getMessage());
		}
	}
	
	@Test
	@Transactional
	public void testAvaliarServicoDeOutros() throws Exception {
		Cliente cliente1 = (Cliente) us.getByLogin(this.cliente1.getLogin());
		Cliente cliente2 = (Cliente) us.getByLogin(this.cliente2.getLogin());
		Fornecedor fornecedor = (Fornecedor) us.getByLogin(fornecedor1.getLogin());
		
		Servico servicoCadastrado = ss.criarServico(cliente1, servico1);
		Servico servicoAceito = ss.setServicoParaFornecedor(servicoCadastrado, fornecedor);
		Servico servicoConcluido = ss.concluirServico(servicoAceito, (Fornecedor) fornecedor);
		
		Avaliacao avaliacaoClienteParaFornecedor = new Avaliacao(3.5);
		AvaliacaoDTO avaliacaoParaFornecedorDTO = new AvaliacaoDTO(avaliacaoClienteParaFornecedor, servicoConcluido);
		
		assertFalse(servicoConcluido.isClienteAvaliou());
		assertFalse(servicoConcluido.isFornecedorAvaliou());
		
		// O usuario tenta avaliar um servico que ele nao participou
		try {
			avaliacaoService.avaliarUsuario(cliente2, avaliacaoParaFornecedorDTO);
		} catch(Exception e) {
			assertEquals("Só pode avaliar usuários que já prestaram serviços para você!", e.getMessage());
		}
		
		assertFalse(servicoConcluido.isClienteAvaliou());
		assertFalse(servicoConcluido.isFornecedorAvaliou());
		
	}

}
