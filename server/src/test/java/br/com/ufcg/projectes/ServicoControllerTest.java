package br.com.ufcg.projectes;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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
import br.com.ufcg.domain.Endereco;
import br.com.ufcg.domain.Especialidade;
import br.com.ufcg.domain.Fornecedor;
import br.com.ufcg.domain.Servico;
import br.com.ufcg.domain.Usuario;
import br.com.ufcg.service.ServicoService;
import br.com.ufcg.service.UsuarioService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServicoControllerTest {

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
	
	private Especialidade especialidade1;
	private Especialidade especialidade2;
	private Especialidade especialidade3;
	private Usuario cliente1;
	private Usuario cliente2;
	private Usuario fornecedor1;
	private Usuario fornecedor2;
	private LocalDate data1;
	private LocalDate data2;
	private LocalTime time1;
	private LocalTime time2;
	private Endereco endereco1;
	private Endereco endereco2;
	private Servico servico1;
	private Servico servico2;
	
	
	@Before
	@Transactional
	public void setUp() {
		especialidade1 = new Especialidade("encanador");
		especialidade2 = new Especialidade("motorista");
		especialidade3 = new Especialidade("eletricista");
		
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
		
		fornecedor1 = new Fornecedor("Carlos Alberto dos Anjos", "carlosaba", "/imgs/foto.png",
				"carlos.alberto1@gmail.com", "123456789", listaEspecialidades1);
		fornecedor2 = new Fornecedor("Jose Thiago Dos Santos", "jthiago", "/imgs/foto.png",
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
		
		endereco1 = new Endereco();
		endereco1.setBairro("liberdade");
		endereco1.setRua("rua souza e lima");
		endereco1.setNumero("129");
		
		endereco2 = new Endereco();
		endereco2.setBairro("catole");
		endereco2.setNumero("456");
		endereco2.setRua("rua francisco");
		
		data1 = LocalDate.now().plusDays(40);
		data2 = LocalDate.now().plusDays(5);
		
		time1 = LocalTime.of(15, 0);
		time2 = LocalTime.of(12,30);
		
		BigDecimal valor1 = BigDecimal.valueOf(25.99);
		BigDecimal valor2 = BigDecimal.valueOf(359);
		
		servico1 = new Servico("encanador", "pia quebrada", data1, time1, valor1, endereco1);
		servico2 = new Servico("motorista", "uma viagem", data2, time2, valor2, endereco2);
		
		
	}
	
	@Test
	@Transactional
	public void testClienteCadastraServicoValido() throws Exception {
		Cliente cliente = (Cliente) us.getByLogin("pedrow");
		Servico servico1 = this.servico1;
		Servico servico2 = this.servico2;
		
		try {
			assertEquals(0, ss.getServicosCliente(cliente).size());
			ss.criarServico(cliente, servico1);
			assertEquals(1, ss.getServicosCliente(cliente).size());
			ss.criarServico(cliente, servico2);
			assertEquals(2, ss.getServicosCliente(cliente).size());
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	@Transactional
	public void testClienteCadastraServicoDataInvalida() throws Exception {
		Usuario cliente = us.getByLogin(cliente2.getLogin());
		
		// Data de 200 dias atras
		LocalDate dataUltrapassada = LocalDate.now().minusDays(200);
		
		BigDecimal valor = BigDecimal.valueOf(6500);
		Servico servico = new Servico("encanador", "projeto de ponte", dataUltrapassada, time1, valor, endereco1);
		
		try {
			ss.criarServico(cliente, servico);
		} catch(Exception e) {
			assertEquals("Data do serviço não pode ser uma data passada.", e.getMessage());
		}
		
		LocalDate dataNula = null;
		
		Servico servico2 = new Servico("encanador", "projeto de ponte", dataNula, time1, valor, endereco1);
		
		try {
			ss.criarServico(cliente, servico2);
		} catch(Exception e) {
			assertEquals("A data do serviço deve ser informada.", e.getMessage());
		}
		
		// Data 5 anos a mais da data de hoje.
		LocalDate dataMuitoFutura = LocalDate.now().plusYears(5);
		Servico servico3 = new Servico("encanador", "projeto de ponte", dataMuitoFutura, time1, valor, endereco1);
		
		try {
			ss.criarServico(cliente, servico3);
		} catch(Exception e) {
			assertEquals("Data do serviço inválida, só pode cadastrar serviços com 3 meses de diferença.", e.getMessage());
		}
		
	}
	
	@Test
	@Transactional
	public void testClienteCadastraServicoHorarioInvalido() throws Exception {
		Usuario cliente = us.getByLogin(cliente1.getLogin());
		BigDecimal valor = BigDecimal.valueOf(25);
		LocalTime muitoCedo = LocalTime.of(5, 0);
		
		Servico servico1 = new Servico("motorista", "me levar ao hospital", data1, muitoCedo, valor, endereco1);
		
		try {
			ss.criarServico(cliente, servico1);
		} catch(Exception e) {
			assertEquals("Desculpe, só é permitido marcar serviços entre os horários de 7h - 20h", e.getMessage());
		}
		
		LocalTime muitoTarde = LocalTime.of(23, 50);
		Servico servico2 = new Servico("motorista", "me levar ao hospital", data1, muitoTarde, valor, endereco1);
		
		try {
			ss.criarServico(cliente, servico2);
		} catch(Exception e) {
			assertEquals("Desculpe, só é permitido marcar serviços entre os horários de 7h - 20h", e.getMessage());
		}
		
		LocalTime horaNula = null;
		Servico servico3 = new Servico("motorista", "me levar ao hospital", data1, horaNula, valor, endereco1);
		
		try {
			ss.criarServico(cliente, servico3);
		} catch(Exception e) {
			assertEquals("A hora do serviço deve ser informada.", e.getMessage());
		}
	}
	
	
}
