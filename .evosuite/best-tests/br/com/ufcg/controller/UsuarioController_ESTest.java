/*
 * This file was automatically generated by EvoSuite
 * Mon Nov 12 04:01:01 GMT 2018
 */

package br.com.ufcg.controller;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import static org.evosuite.runtime.EvoAssertions.*;
import br.com.ufcg.controller.UsuarioController;
import br.com.ufcg.domain.Cliente;
import br.com.ufcg.domain.Especialidade;
import br.com.ufcg.domain.Fornecedor;
import br.com.ufcg.domain.Usuario;
import br.com.ufcg.domain.vo.AlterarDadosForm;
import br.com.ufcg.domain.vo.LoginForm;
import br.com.ufcg.domain.vo.NovaSenhaForm;
import br.com.ufcg.repository.AvaliacaoRepository;
import br.com.ufcg.repository.EspecialidadeRepository;
import br.com.ufcg.repository.ServicoRepository;
import br.com.ufcg.repository.UsuarioRepository;
import br.com.ufcg.service.AvaliacaoService;
import br.com.ufcg.service.EspecialidadeService;
import br.com.ufcg.service.ServicoService;
import br.com.ufcg.service.UsuarioService;
import br.com.ufcg.util.response.Response;
import java.util.List;
import java.util.Stack;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.evosuite.runtime.javaee.injection.Injector;
import org.junit.runner.RunWith;
import org.springframework.http.ResponseEntity;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class UsuarioController_ESTest extends UsuarioController_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      UsuarioController usuarioController0 = new UsuarioController();
      UsuarioService usuarioService0 = new UsuarioService();
      AvaliacaoService avaliacaoService0 = new AvaliacaoService();
      AvaliacaoRepository avaliacaoRepository0 = mock(AvaliacaoRepository.class, new ViolatedAssumptionAnswer());
      Injector.inject(avaliacaoService0, (Class<?>) AvaliacaoService.class, "avaliacaoRepository", (Object) avaliacaoRepository0);
      ServicoService servicoService0 = new ServicoService();
      ServicoRepository servicoRepository0 = mock(ServicoRepository.class, new ViolatedAssumptionAnswer());
      Injector.inject(servicoService0, (Class<?>) ServicoService.class, "servicoRepository", (Object) servicoRepository0);
      Injector.validateBean(servicoService0, (Class<?>) ServicoService.class);
      Injector.inject(avaliacaoService0, (Class<?>) AvaliacaoService.class, "servicoService", (Object) servicoService0);
      Injector.inject(avaliacaoService0, (Class<?>) AvaliacaoService.class, "usuarioService", (Object) usuarioService0);
      Injector.validateBean(avaliacaoService0, (Class<?>) AvaliacaoService.class);
      Injector.inject(usuarioService0, (Class<?>) UsuarioService.class, "avaliacaoService", (Object) avaliacaoService0);
      EspecialidadeService especialidadeService0 = new EspecialidadeService();
      EspecialidadeRepository especialidadeRepository0 = mock(EspecialidadeRepository.class, new ViolatedAssumptionAnswer());
      Injector.inject(especialidadeService0, (Class<?>) EspecialidadeService.class, "especialidadeRepository", (Object) especialidadeRepository0);
      Injector.validateBean(especialidadeService0, (Class<?>) EspecialidadeService.class);
      Vector<Especialidade> vector0 = new Vector<Especialidade>();
      Fornecedor fornecedor0 = new Fornecedor("Preencha todos os campos do formul\u00E1rio!", "av*-':X($.[]30_>", "u|pi_@`j-j`@IaL", "Gateway Timeout", "ProjetoES", vector0);
      Injector.inject(usuarioService0, (Class<?>) UsuarioService.class, "especialidadeService", (Object) especialidadeService0);
      UsuarioRepository usuarioRepository0 = mock(UsuarioRepository.class, new ViolatedAssumptionAnswer());
      doReturn(fornecedor0).when(usuarioRepository0).findByLogin(anyString());
      Injector.inject(usuarioService0, (Class<?>) UsuarioService.class, "usuarioRepository", (Object) usuarioRepository0);
      Injector.validateBean(usuarioService0, (Class<?>) UsuarioService.class);
      Injector.inject(usuarioController0, (Class<?>) UsuarioController.class, "usuarioService", (Object) usuarioService0);
      Injector.validateBean(usuarioController0, (Class<?>) UsuarioController.class);
      Stack<Especialidade> stack0 = new Stack<Especialidade>();
      Fornecedor fornecedor1 = new Fornecedor("~DF", "r`OH6Ytm%5ewd.eY", "i&K9o](y", "Login efetuado com sucesso !", " ", stack0);
      HttpServletRequest httpServletRequest0 = mock(HttpServletRequest.class, new ViolatedAssumptionAnswer());
      doReturn(fornecedor1).when(httpServletRequest0).getAttribute(anyString());
      NovaSenhaForm novaSenhaForm0 = new NovaSenhaForm("av*-':X($.[]30_>", "Login efetuado com sucesso !", "LOCKED");
      ResponseEntity<Response> responseEntity0 = usuarioController0.atualizarSenha(httpServletRequest0, novaSenhaForm0);
      assertTrue(responseEntity0.hasBody());
  }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      UsuarioController usuarioController0 = new UsuarioController();
      UsuarioService usuarioService0 = new UsuarioService();
      AvaliacaoService avaliacaoService0 = new AvaliacaoService();
      AvaliacaoRepository avaliacaoRepository0 = mock(AvaliacaoRepository.class, new ViolatedAssumptionAnswer());
      Injector.inject(avaliacaoService0, (Class<?>) AvaliacaoService.class, "avaliacaoRepository", (Object) avaliacaoRepository0);
      ServicoService servicoService0 = new ServicoService();
      ServicoRepository servicoRepository0 = mock(ServicoRepository.class, new ViolatedAssumptionAnswer());
      Injector.inject(servicoService0, (Class<?>) ServicoService.class, "servicoRepository", (Object) servicoRepository0);
      Injector.validateBean(servicoService0, (Class<?>) ServicoService.class);
      Injector.inject(avaliacaoService0, (Class<?>) AvaliacaoService.class, "servicoService", (Object) servicoService0);
      Injector.inject(avaliacaoService0, (Class<?>) AvaliacaoService.class, "usuarioService", (Object) usuarioService0);
      Injector.validateBean(avaliacaoService0, (Class<?>) AvaliacaoService.class);
      Injector.inject(usuarioService0, (Class<?>) UsuarioService.class, "avaliacaoService", (Object) avaliacaoService0);
      EspecialidadeService especialidadeService0 = new EspecialidadeService();
      EspecialidadeRepository especialidadeRepository0 = mock(EspecialidadeRepository.class, new ViolatedAssumptionAnswer());
      Injector.inject(especialidadeService0, (Class<?>) EspecialidadeService.class, "especialidadeRepository", (Object) especialidadeRepository0);
      Injector.validateBean(especialidadeService0, (Class<?>) EspecialidadeService.class);
      Injector.inject(usuarioService0, (Class<?>) UsuarioService.class, "especialidadeService", (Object) especialidadeService0);
      UsuarioRepository usuarioRepository0 = mock(UsuarioRepository.class, new ViolatedAssumptionAnswer());
      doReturn((Usuario) null).when(usuarioRepository0).findByLogin(anyString());
      Injector.inject(usuarioService0, (Class<?>) UsuarioService.class, "usuarioRepository", (Object) usuarioRepository0);
      Injector.validateBean(usuarioService0, (Class<?>) UsuarioService.class);
      Injector.inject(usuarioController0, (Class<?>) UsuarioController.class, "usuarioService", (Object) usuarioService0);
      Injector.validateBean(usuarioController0, (Class<?>) UsuarioController.class);
      LoginForm loginForm0 = new LoginForm();
      loginForm0.setLogin("u*ACU&Xhm6");
      loginForm0.setSenha("Senha incorreta");
      ResponseEntity<Response> responseEntity0 = usuarioController0.login(loginForm0);
      assertEquals(404, responseEntity0.getStatusCodeValue());
  }

  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      UsuarioController usuarioController0 = new UsuarioController();
      UsuarioService usuarioService0 = new UsuarioService();
      AvaliacaoService avaliacaoService0 = new AvaliacaoService();
      AvaliacaoRepository avaliacaoRepository0 = mock(AvaliacaoRepository.class, new ViolatedAssumptionAnswer());
      Injector.inject(avaliacaoService0, (Class<?>) AvaliacaoService.class, "avaliacaoRepository", (Object) avaliacaoRepository0);
      ServicoService servicoService0 = new ServicoService();
      ServicoRepository servicoRepository0 = mock(ServicoRepository.class, new ViolatedAssumptionAnswer());
      Injector.inject(servicoService0, (Class<?>) ServicoService.class, "servicoRepository", (Object) servicoRepository0);
      Injector.validateBean(servicoService0, (Class<?>) ServicoService.class);
      Injector.inject(avaliacaoService0, (Class<?>) AvaliacaoService.class, "servicoService", (Object) servicoService0);
      Injector.inject(avaliacaoService0, (Class<?>) AvaliacaoService.class, "usuarioService", (Object) usuarioService0);
      Injector.validateBean(avaliacaoService0, (Class<?>) AvaliacaoService.class);
      Injector.inject(usuarioService0, (Class<?>) UsuarioService.class, "avaliacaoService", (Object) avaliacaoService0);
      EspecialidadeService especialidadeService0 = new EspecialidadeService();
      EspecialidadeRepository especialidadeRepository0 = mock(EspecialidadeRepository.class, new ViolatedAssumptionAnswer());
      Injector.inject(especialidadeService0, (Class<?>) EspecialidadeService.class, "especialidadeRepository", (Object) especialidadeRepository0);
      Injector.validateBean(especialidadeService0, (Class<?>) EspecialidadeService.class);
      Injector.inject(usuarioService0, (Class<?>) UsuarioService.class, "especialidadeService", (Object) especialidadeService0);
      UsuarioRepository usuarioRepository0 = mock(UsuarioRepository.class, new ViolatedAssumptionAnswer());
      Injector.inject(usuarioService0, (Class<?>) UsuarioService.class, "usuarioRepository", (Object) usuarioRepository0);
      Injector.validateBean(usuarioService0, (Class<?>) UsuarioService.class);
      Injector.inject(usuarioController0, (Class<?>) UsuarioController.class, "usuarioService", (Object) usuarioService0);
      Injector.validateBean(usuarioController0, (Class<?>) UsuarioController.class);
      HttpServletRequest httpServletRequest0 = mock(HttpServletRequest.class, new ViolatedAssumptionAnswer());
      doReturn((Object) null).when(httpServletRequest0).getAttribute(anyString());
      HttpServletRequestWrapper httpServletRequestWrapper0 = new HttpServletRequestWrapper(httpServletRequest0);
      ResponseEntity<Response> responseEntity0 = usuarioController0.getUsuario(httpServletRequestWrapper0);
      assertEquals(400, responseEntity0.getStatusCodeValue());
  }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      UsuarioController usuarioController0 = new UsuarioController();
      UsuarioService usuarioService0 = new UsuarioService();
      AvaliacaoService avaliacaoService0 = new AvaliacaoService();
      AvaliacaoRepository avaliacaoRepository0 = mock(AvaliacaoRepository.class, new ViolatedAssumptionAnswer());
      Injector.inject(avaliacaoService0, (Class<?>) AvaliacaoService.class, "avaliacaoRepository", (Object) avaliacaoRepository0);
      ServicoService servicoService0 = new ServicoService();
      ServicoRepository servicoRepository0 = mock(ServicoRepository.class, new ViolatedAssumptionAnswer());
      Injector.inject(servicoService0, (Class<?>) ServicoService.class, "servicoRepository", (Object) servicoRepository0);
      Injector.validateBean(servicoService0, (Class<?>) ServicoService.class);
      Injector.inject(avaliacaoService0, (Class<?>) AvaliacaoService.class, "servicoService", (Object) servicoService0);
      Injector.inject(avaliacaoService0, (Class<?>) AvaliacaoService.class, "usuarioService", (Object) usuarioService0);
      Injector.validateBean(avaliacaoService0, (Class<?>) AvaliacaoService.class);
      Injector.inject(usuarioService0, (Class<?>) UsuarioService.class, "avaliacaoService", (Object) avaliacaoService0);
      EspecialidadeService especialidadeService0 = new EspecialidadeService();
      EspecialidadeRepository especialidadeRepository0 = mock(EspecialidadeRepository.class, new ViolatedAssumptionAnswer());
      Injector.inject(especialidadeService0, (Class<?>) EspecialidadeService.class, "especialidadeRepository", (Object) especialidadeRepository0);
      Injector.validateBean(especialidadeService0, (Class<?>) EspecialidadeService.class);
      Injector.inject(usuarioService0, (Class<?>) UsuarioService.class, "especialidadeService", (Object) especialidadeService0);
      UsuarioRepository usuarioRepository0 = mock(UsuarioRepository.class, new ViolatedAssumptionAnswer());
      Injector.inject(usuarioService0, (Class<?>) UsuarioService.class, "usuarioRepository", (Object) usuarioRepository0);
      Injector.validateBean(usuarioService0, (Class<?>) UsuarioService.class);
      Injector.inject(usuarioController0, (Class<?>) UsuarioController.class, "usuarioService", (Object) usuarioService0);
      Injector.validateBean(usuarioController0, (Class<?>) UsuarioController.class);
      HttpServletRequest httpServletRequest0 = mock(HttpServletRequest.class, new ViolatedAssumptionAnswer());
      doReturn((Object) null).when(httpServletRequest0).getAttribute(anyString());
      HttpServletRequestWrapper httpServletRequestWrapper0 = new HttpServletRequestWrapper(httpServletRequest0);
      AlterarDadosForm alterarDadosForm0 = new AlterarDadosForm();
      ResponseEntity<Response> responseEntity0 = usuarioController0.atualizarDados(httpServletRequestWrapper0, alterarDadosForm0);
      assertTrue(responseEntity0.hasBody());
  }

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      UsuarioController usuarioController0 = new UsuarioController();
      UsuarioService usuarioService0 = new UsuarioService();
      AvaliacaoService avaliacaoService0 = new AvaliacaoService();
      AvaliacaoRepository avaliacaoRepository0 = mock(AvaliacaoRepository.class, new ViolatedAssumptionAnswer());
      Injector.inject(avaliacaoService0, (Class<?>) AvaliacaoService.class, "avaliacaoRepository", (Object) avaliacaoRepository0);
      ServicoService servicoService0 = new ServicoService();
      ServicoRepository servicoRepository0 = mock(ServicoRepository.class, new ViolatedAssumptionAnswer());
      Injector.inject(servicoService0, (Class<?>) ServicoService.class, "servicoRepository", (Object) servicoRepository0);
      Injector.validateBean(servicoService0, (Class<?>) ServicoService.class);
      Injector.inject(avaliacaoService0, (Class<?>) AvaliacaoService.class, "servicoService", (Object) servicoService0);
      UsuarioService usuarioService1 = mock(UsuarioService.class, new ViolatedAssumptionAnswer());
      Injector.inject(avaliacaoService0, (Class<?>) AvaliacaoService.class, "usuarioService", (Object) usuarioService1);
      Injector.validateBean(avaliacaoService0, (Class<?>) AvaliacaoService.class);
      Injector.inject(usuarioService0, (Class<?>) UsuarioService.class, "avaliacaoService", (Object) avaliacaoService0);
      EspecialidadeService especialidadeService0 = new EspecialidadeService();
      EspecialidadeRepository especialidadeRepository0 = mock(EspecialidadeRepository.class, new ViolatedAssumptionAnswer());
      Injector.inject(especialidadeService0, (Class<?>) EspecialidadeService.class, "especialidadeRepository", (Object) especialidadeRepository0);
      Injector.validateBean(especialidadeService0, (Class<?>) EspecialidadeService.class);
      Injector.inject(usuarioService0, (Class<?>) UsuarioService.class, "especialidadeService", (Object) especialidadeService0);
      UsuarioRepository usuarioRepository0 = mock(UsuarioRepository.class, new ViolatedAssumptionAnswer());
      doReturn((List) null).when(usuarioRepository0).findAll();
      Injector.inject(usuarioService0, (Class<?>) UsuarioService.class, "usuarioRepository", (Object) usuarioRepository0);
      Injector.validateBean(usuarioService0, (Class<?>) UsuarioService.class);
      Injector.inject(usuarioController0, (Class<?>) UsuarioController.class, "usuarioService", (Object) usuarioService0);
      Injector.validateBean(usuarioController0, (Class<?>) UsuarioController.class);
      Fornecedor fornecedor0 = new Fornecedor();
      usuarioController0.cadastrarFornecedor(fornecedor0);
      usuarioController0.getUsuario((HttpServletRequest) null);
      Stack<Especialidade> stack0 = new Stack<Especialidade>();
      AlterarDadosForm alterarDadosForm0 = new AlterarDadosForm("user", "32ai", "", stack0, "Usuario nao existe");
      usuarioController0.atualizarDados((HttpServletRequest) null, alterarDadosForm0);
      LoginForm loginForm0 = new LoginForm();
      usuarioController0.login(loginForm0);
      // Undeclared exception!
      try { 
        usuarioController0.listaClientes();
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("br.com.ufcg.service.UsuarioService", e);
      }
  }

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      UsuarioController usuarioController0 = new UsuarioController();
      UsuarioService usuarioService0 = new UsuarioService();
      AvaliacaoService avaliacaoService0 = new AvaliacaoService();
      AvaliacaoRepository avaliacaoRepository0 = mock(AvaliacaoRepository.class, new ViolatedAssumptionAnswer());
      Injector.inject(avaliacaoService0, (Class<?>) AvaliacaoService.class, "avaliacaoRepository", (Object) avaliacaoRepository0);
      ServicoService servicoService0 = new ServicoService();
      ServicoRepository servicoRepository0 = mock(ServicoRepository.class, new ViolatedAssumptionAnswer());
      Injector.inject(servicoService0, (Class<?>) ServicoService.class, "servicoRepository", (Object) servicoRepository0);
      Injector.validateBean(servicoService0, (Class<?>) ServicoService.class);
      Injector.inject(avaliacaoService0, (Class<?>) AvaliacaoService.class, "servicoService", (Object) servicoService0);
      Injector.inject(avaliacaoService0, (Class<?>) AvaliacaoService.class, "usuarioService", (Object) usuarioService0);
      Injector.validateBean(avaliacaoService0, (Class<?>) AvaliacaoService.class);
      Injector.inject(usuarioService0, (Class<?>) UsuarioService.class, "avaliacaoService", (Object) avaliacaoService0);
      EspecialidadeService especialidadeService0 = new EspecialidadeService();
      EspecialidadeRepository especialidadeRepository0 = mock(EspecialidadeRepository.class, new ViolatedAssumptionAnswer());
      Injector.inject(especialidadeService0, (Class<?>) EspecialidadeService.class, "especialidadeRepository", (Object) especialidadeRepository0);
      Injector.validateBean(especialidadeService0, (Class<?>) EspecialidadeService.class);
      Injector.inject(usuarioService0, (Class<?>) UsuarioService.class, "especialidadeService", (Object) especialidadeService0);
      UsuarioRepository usuarioRepository0 = mock(UsuarioRepository.class, new ViolatedAssumptionAnswer());
      Injector.inject(usuarioService0, (Class<?>) UsuarioService.class, "usuarioRepository", (Object) usuarioRepository0);
      Injector.validateBean(usuarioService0, (Class<?>) UsuarioService.class);
      Injector.inject(usuarioController0, (Class<?>) UsuarioController.class, "usuarioService", (Object) usuarioService0);
      Injector.validateBean(usuarioController0, (Class<?>) UsuarioController.class);
      // Undeclared exception!
      try { 
        usuarioController0.login((LoginForm) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("br.com.ufcg.controller.UsuarioController", e);
      }
  }

  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      UsuarioController usuarioController0 = new UsuarioController();
      UsuarioService usuarioService0 = new UsuarioService();
      AvaliacaoService avaliacaoService0 = new AvaliacaoService();
      AvaliacaoRepository avaliacaoRepository0 = mock(AvaliacaoRepository.class, new ViolatedAssumptionAnswer());
      Injector.inject(avaliacaoService0, (Class<?>) AvaliacaoService.class, "avaliacaoRepository", (Object) avaliacaoRepository0);
      ServicoService servicoService0 = new ServicoService();
      ServicoRepository servicoRepository0 = mock(ServicoRepository.class, new ViolatedAssumptionAnswer());
      Injector.inject(servicoService0, (Class<?>) ServicoService.class, "servicoRepository", (Object) servicoRepository0);
      Injector.validateBean(servicoService0, (Class<?>) ServicoService.class);
      Injector.inject(avaliacaoService0, (Class<?>) AvaliacaoService.class, "servicoService", (Object) servicoService0);
      Injector.inject(avaliacaoService0, (Class<?>) AvaliacaoService.class, "usuarioService", (Object) usuarioService0);
      Injector.validateBean(avaliacaoService0, (Class<?>) AvaliacaoService.class);
      Injector.inject(usuarioService0, (Class<?>) UsuarioService.class, "avaliacaoService", (Object) avaliacaoService0);
      EspecialidadeService especialidadeService0 = new EspecialidadeService();
      EspecialidadeRepository especialidadeRepository0 = mock(EspecialidadeRepository.class, new ViolatedAssumptionAnswer());
      Injector.inject(especialidadeService0, (Class<?>) EspecialidadeService.class, "especialidadeRepository", (Object) especialidadeRepository0);
      Injector.validateBean(especialidadeService0, (Class<?>) EspecialidadeService.class);
      Injector.inject(usuarioService0, (Class<?>) UsuarioService.class, "especialidadeService", (Object) especialidadeService0);
      UsuarioRepository usuarioRepository0 = mock(UsuarioRepository.class, new ViolatedAssumptionAnswer());
      doReturn((List<Usuario>) null).when(usuarioRepository0).findAll();
      Injector.inject(usuarioService0, (Class<?>) UsuarioService.class, "usuarioRepository", (Object) usuarioRepository0);
      Injector.validateBean(usuarioService0, (Class<?>) UsuarioService.class);
      Injector.inject(usuarioController0, (Class<?>) UsuarioController.class, "usuarioService", (Object) usuarioService0);
      Injector.validateBean(usuarioController0, (Class<?>) UsuarioController.class);
      // Undeclared exception!
      try { 
        usuarioController0.listaFornecedores();
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("br.com.ufcg.service.UsuarioService", e);
      }
  }

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      UsuarioController usuarioController0 = new UsuarioController();
      UsuarioService usuarioService0 = new UsuarioService();
      AvaliacaoService avaliacaoService0 = new AvaliacaoService();
      AvaliacaoRepository avaliacaoRepository0 = mock(AvaliacaoRepository.class, new ViolatedAssumptionAnswer());
      Injector.inject(avaliacaoService0, (Class<?>) AvaliacaoService.class, "avaliacaoRepository", (Object) avaliacaoRepository0);
      ServicoService servicoService0 = new ServicoService();
      ServicoRepository servicoRepository0 = mock(ServicoRepository.class, new ViolatedAssumptionAnswer());
      Injector.inject(servicoService0, (Class<?>) ServicoService.class, "servicoRepository", (Object) servicoRepository0);
      Injector.validateBean(servicoService0, (Class<?>) ServicoService.class);
      Injector.inject(avaliacaoService0, (Class<?>) AvaliacaoService.class, "servicoService", (Object) servicoService0);
      Injector.inject(avaliacaoService0, (Class<?>) AvaliacaoService.class, "usuarioService", (Object) usuarioService0);
      Injector.validateBean(avaliacaoService0, (Class<?>) AvaliacaoService.class);
      Injector.inject(usuarioService0, (Class<?>) UsuarioService.class, "avaliacaoService", (Object) avaliacaoService0);
      EspecialidadeService especialidadeService0 = new EspecialidadeService();
      EspecialidadeRepository especialidadeRepository0 = mock(EspecialidadeRepository.class, new ViolatedAssumptionAnswer());
      Injector.inject(especialidadeService0, (Class<?>) EspecialidadeService.class, "especialidadeRepository", (Object) especialidadeRepository0);
      Injector.validateBean(especialidadeService0, (Class<?>) EspecialidadeService.class);
      Injector.inject(usuarioService0, (Class<?>) UsuarioService.class, "especialidadeService", (Object) especialidadeService0);
      UsuarioRepository usuarioRepository0 = mock(UsuarioRepository.class, new ViolatedAssumptionAnswer());
      Injector.inject(usuarioService0, (Class<?>) UsuarioService.class, "usuarioRepository", (Object) usuarioRepository0);
      Injector.validateBean(usuarioService0, (Class<?>) UsuarioService.class);
      Injector.inject(usuarioController0, (Class<?>) UsuarioController.class, "usuarioService", (Object) usuarioService0);
      Injector.validateBean(usuarioController0, (Class<?>) UsuarioController.class);
      HttpServletRequest httpServletRequest0 = mock(HttpServletRequest.class, new ViolatedAssumptionAnswer());
      HttpServletRequestWrapper httpServletRequestWrapper0 = new HttpServletRequestWrapper(httpServletRequest0);
      httpServletRequestWrapper0.setRequest(httpServletRequestWrapper0);
      // Undeclared exception!
      try { 
        usuarioController0.atualizarSenha(httpServletRequestWrapper0, (NovaSenhaForm) null);
        fail("Expecting exception: StackOverflowError");
      
      } catch(StackOverflowError e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      UsuarioController usuarioController0 = new UsuarioController();
      UsuarioService usuarioService0 = new UsuarioService();
      AvaliacaoService avaliacaoService0 = new AvaliacaoService();
      AvaliacaoRepository avaliacaoRepository0 = mock(AvaliacaoRepository.class, new ViolatedAssumptionAnswer());
      Injector.inject(avaliacaoService0, (Class<?>) AvaliacaoService.class, "avaliacaoRepository", (Object) avaliacaoRepository0);
      ServicoService servicoService0 = new ServicoService();
      ServicoRepository servicoRepository0 = mock(ServicoRepository.class, new ViolatedAssumptionAnswer());
      Injector.inject(servicoService0, (Class<?>) ServicoService.class, "servicoRepository", (Object) servicoRepository0);
      Injector.validateBean(servicoService0, (Class<?>) ServicoService.class);
      Injector.inject(avaliacaoService0, (Class<?>) AvaliacaoService.class, "servicoService", (Object) servicoService0);
      Injector.inject(avaliacaoService0, (Class<?>) AvaliacaoService.class, "usuarioService", (Object) usuarioService0);
      Injector.validateBean(avaliacaoService0, (Class<?>) AvaliacaoService.class);
      Injector.inject(usuarioService0, (Class<?>) UsuarioService.class, "avaliacaoService", (Object) avaliacaoService0);
      EspecialidadeService especialidadeService0 = new EspecialidadeService();
      EspecialidadeRepository especialidadeRepository0 = mock(EspecialidadeRepository.class, new ViolatedAssumptionAnswer());
      Injector.inject(especialidadeService0, (Class<?>) EspecialidadeService.class, "especialidadeRepository", (Object) especialidadeRepository0);
      Injector.validateBean(especialidadeService0, (Class<?>) EspecialidadeService.class);
      Vector<Especialidade> vector0 = new Vector<Especialidade>();
      Fornecedor fornecedor0 = new Fornecedor("Preencha todos os campos do formul\u00E1rio!", "a*-':X($.[]30_V", "Preencha todos os campos do formul\u00E1rio!", "Gateway Timeout", "", vector0);
      Injector.inject(usuarioService0, (Class<?>) UsuarioService.class, "especialidadeService", (Object) especialidadeService0);
      UsuarioRepository usuarioRepository0 = mock(UsuarioRepository.class, new ViolatedAssumptionAnswer());
      doReturn((Usuario) null).when(usuarioRepository0).findByLogin(anyString());
      Injector.inject(usuarioService0, (Class<?>) UsuarioService.class, "usuarioRepository", (Object) usuarioRepository0);
      Injector.validateBean(usuarioService0, (Class<?>) UsuarioService.class);
      Injector.inject(usuarioController0, (Class<?>) UsuarioController.class, "usuarioService", (Object) usuarioService0);
      Injector.validateBean(usuarioController0, (Class<?>) UsuarioController.class);
      LoginForm loginForm0 = new LoginForm();
      loginForm0.setLogin("u*ACU&Xhm6");
      loginForm0.setSenha("");
      usuarioController0.login(loginForm0);
  }

  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      UsuarioController usuarioController0 = new UsuarioController();
      UsuarioService usuarioService0 = new UsuarioService();
      AvaliacaoService avaliacaoService0 = new AvaliacaoService();
      AvaliacaoRepository avaliacaoRepository0 = mock(AvaliacaoRepository.class, new ViolatedAssumptionAnswer());
      Injector.inject(avaliacaoService0, (Class<?>) AvaliacaoService.class, "avaliacaoRepository", (Object) avaliacaoRepository0);
      ServicoService servicoService0 = new ServicoService();
      ServicoRepository servicoRepository0 = mock(ServicoRepository.class, new ViolatedAssumptionAnswer());
      Injector.inject(servicoService0, (Class<?>) ServicoService.class, "servicoRepository", (Object) servicoRepository0);
      Injector.validateBean(servicoService0, (Class<?>) ServicoService.class);
      Injector.inject(avaliacaoService0, (Class<?>) AvaliacaoService.class, "servicoService", (Object) servicoService0);
      Injector.inject(avaliacaoService0, (Class<?>) AvaliacaoService.class, "usuarioService", (Object) usuarioService0);
      Injector.validateBean(avaliacaoService0, (Class<?>) AvaliacaoService.class);
      Injector.inject(usuarioService0, (Class<?>) UsuarioService.class, "avaliacaoService", (Object) avaliacaoService0);
      EspecialidadeService especialidadeService0 = new EspecialidadeService();
      EspecialidadeRepository especialidadeRepository0 = mock(EspecialidadeRepository.class, new ViolatedAssumptionAnswer());
      Injector.inject(especialidadeService0, (Class<?>) EspecialidadeService.class, "especialidadeRepository", (Object) especialidadeRepository0);
      Injector.validateBean(especialidadeService0, (Class<?>) EspecialidadeService.class);
      Vector<Especialidade> vector0 = new Vector<Especialidade>();
      Fornecedor fornecedor0 = new Fornecedor("Preencha todos os campos do formul\u00E1rio!", "av*-':X($.[]30_>", "u|pi_@`j-j`@IaL", "Gateway Timeout", "ProjetoES", vector0);
      Injector.inject(usuarioService0, (Class<?>) UsuarioService.class, "especialidadeService", (Object) especialidadeService0);
      UsuarioRepository usuarioRepository0 = mock(UsuarioRepository.class, new ViolatedAssumptionAnswer());
      doReturn(fornecedor0).when(usuarioRepository0).findByLogin(anyString());
      Injector.inject(usuarioService0, (Class<?>) UsuarioService.class, "usuarioRepository", (Object) usuarioRepository0);
      Injector.validateBean(usuarioService0, (Class<?>) UsuarioService.class);
      Injector.inject(usuarioController0, (Class<?>) UsuarioController.class, "usuarioService", (Object) usuarioService0);
      Injector.validateBean(usuarioController0, (Class<?>) UsuarioController.class);
      LoginForm loginForm0 = new LoginForm();
      loginForm0.setLogin("u*ACU&Xhm6");
      loginForm0.setSenha("e}hacreK");
      ResponseEntity<Response> responseEntity0 = usuarioController0.login(loginForm0);
      assertEquals(401, responseEntity0.getStatusCodeValue());
  }

  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      UsuarioController usuarioController0 = new UsuarioController();
      UsuarioService usuarioService0 = new UsuarioService();
      AvaliacaoService avaliacaoService0 = new AvaliacaoService();
      AvaliacaoRepository avaliacaoRepository0 = mock(AvaliacaoRepository.class, new ViolatedAssumptionAnswer());
      Injector.inject(avaliacaoService0, (Class<?>) AvaliacaoService.class, "avaliacaoRepository", (Object) avaliacaoRepository0);
      ServicoService servicoService0 = new ServicoService();
      ServicoRepository servicoRepository0 = mock(ServicoRepository.class, new ViolatedAssumptionAnswer());
      Injector.inject(servicoService0, (Class<?>) ServicoService.class, "servicoRepository", (Object) servicoRepository0);
      Injector.validateBean(servicoService0, (Class<?>) ServicoService.class);
      Injector.inject(avaliacaoService0, (Class<?>) AvaliacaoService.class, "servicoService", (Object) servicoService0);
      Injector.inject(avaliacaoService0, (Class<?>) AvaliacaoService.class, "usuarioService", (Object) usuarioService0);
      Injector.validateBean(avaliacaoService0, (Class<?>) AvaliacaoService.class);
      Injector.inject(usuarioService0, (Class<?>) UsuarioService.class, "avaliacaoService", (Object) avaliacaoService0);
      EspecialidadeService especialidadeService0 = new EspecialidadeService();
      EspecialidadeRepository especialidadeRepository0 = mock(EspecialidadeRepository.class, new ViolatedAssumptionAnswer());
      Injector.inject(especialidadeService0, (Class<?>) EspecialidadeService.class, "especialidadeRepository", (Object) especialidadeRepository0);
      Injector.validateBean(especialidadeService0, (Class<?>) EspecialidadeService.class);
      Injector.inject(usuarioService0, (Class<?>) UsuarioService.class, "especialidadeService", (Object) especialidadeService0);
      UsuarioRepository usuarioRepository0 = mock(UsuarioRepository.class, new ViolatedAssumptionAnswer());
      Injector.inject(usuarioService0, (Class<?>) UsuarioService.class, "usuarioRepository", (Object) usuarioRepository0);
      Injector.validateBean(usuarioService0, (Class<?>) UsuarioService.class);
      Injector.inject(usuarioController0, (Class<?>) UsuarioController.class, "usuarioService", (Object) usuarioService0);
      Injector.validateBean(usuarioController0, (Class<?>) UsuarioController.class);
      LoginForm loginForm0 = new LoginForm();
      loginForm0.setLogin("");
      ResponseEntity<Response> responseEntity0 = usuarioController0.login(loginForm0);
      assertEquals(406, responseEntity0.getStatusCodeValue());
  }

  @Test(timeout = 4000)
  public void test11()  throws Throwable  {
      UsuarioController usuarioController0 = new UsuarioController();
      UsuarioService usuarioService0 = new UsuarioService();
      AvaliacaoService avaliacaoService0 = new AvaliacaoService();
      AvaliacaoRepository avaliacaoRepository0 = mock(AvaliacaoRepository.class, new ViolatedAssumptionAnswer());
      Injector.inject(avaliacaoService0, (Class<?>) AvaliacaoService.class, "avaliacaoRepository", (Object) avaliacaoRepository0);
      ServicoService servicoService0 = new ServicoService();
      ServicoRepository servicoRepository0 = mock(ServicoRepository.class, new ViolatedAssumptionAnswer());
      Injector.inject(servicoService0, (Class<?>) ServicoService.class, "servicoRepository", (Object) servicoRepository0);
      Injector.validateBean(servicoService0, (Class<?>) ServicoService.class);
      Injector.inject(avaliacaoService0, (Class<?>) AvaliacaoService.class, "servicoService", (Object) servicoService0);
      Injector.inject(avaliacaoService0, (Class<?>) AvaliacaoService.class, "usuarioService", (Object) usuarioService0);
      Injector.validateBean(avaliacaoService0, (Class<?>) AvaliacaoService.class);
      Injector.inject(usuarioService0, (Class<?>) UsuarioService.class, "avaliacaoService", (Object) avaliacaoService0);
      EspecialidadeService especialidadeService0 = new EspecialidadeService();
      EspecialidadeRepository especialidadeRepository0 = mock(EspecialidadeRepository.class, new ViolatedAssumptionAnswer());
      Injector.inject(especialidadeService0, (Class<?>) EspecialidadeService.class, "especialidadeRepository", (Object) especialidadeRepository0);
      Injector.validateBean(especialidadeService0, (Class<?>) EspecialidadeService.class);
      Injector.inject(usuarioService0, (Class<?>) UsuarioService.class, "especialidadeService", (Object) especialidadeService0);
      UsuarioRepository usuarioRepository0 = mock(UsuarioRepository.class, new ViolatedAssumptionAnswer());
      Injector.inject(usuarioService0, (Class<?>) UsuarioService.class, "usuarioRepository", (Object) usuarioRepository0);
      Injector.validateBean(usuarioService0, (Class<?>) UsuarioService.class);
      Injector.inject(usuarioController0, (Class<?>) UsuarioController.class, "usuarioService", (Object) usuarioService0);
      Injector.validateBean(usuarioController0, (Class<?>) UsuarioController.class);
      Cliente cliente0 = new Cliente();
      ResponseEntity<Response> responseEntity0 = usuarioController0.cadastrarCliente(cliente0);
      assertTrue(responseEntity0.hasBody());
  }

  @Test(timeout = 4000)
  public void test12()  throws Throwable  {
      UsuarioController usuarioController0 = new UsuarioController();
      UsuarioService usuarioService0 = new UsuarioService();
      AvaliacaoService avaliacaoService0 = new AvaliacaoService();
      AvaliacaoRepository avaliacaoRepository0 = mock(AvaliacaoRepository.class, new ViolatedAssumptionAnswer());
      Injector.inject(avaliacaoService0, (Class<?>) AvaliacaoService.class, "avaliacaoRepository", (Object) avaliacaoRepository0);
      ServicoService servicoService0 = new ServicoService();
      ServicoRepository servicoRepository0 = mock(ServicoRepository.class, new ViolatedAssumptionAnswer());
      Injector.inject(servicoService0, (Class<?>) ServicoService.class, "servicoRepository", (Object) servicoRepository0);
      Injector.validateBean(servicoService0, (Class<?>) ServicoService.class);
      Injector.inject(avaliacaoService0, (Class<?>) AvaliacaoService.class, "servicoService", (Object) servicoService0);
      Injector.inject(avaliacaoService0, (Class<?>) AvaliacaoService.class, "usuarioService", (Object) usuarioService0);
      Injector.validateBean(avaliacaoService0, (Class<?>) AvaliacaoService.class);
      Injector.inject(usuarioService0, (Class<?>) UsuarioService.class, "avaliacaoService", (Object) avaliacaoService0);
      EspecialidadeService especialidadeService0 = new EspecialidadeService();
      EspecialidadeRepository especialidadeRepository0 = mock(EspecialidadeRepository.class, new ViolatedAssumptionAnswer());
      Injector.inject(especialidadeService0, (Class<?>) EspecialidadeService.class, "especialidadeRepository", (Object) especialidadeRepository0);
      Injector.validateBean(especialidadeService0, (Class<?>) EspecialidadeService.class);
      Injector.inject(usuarioService0, (Class<?>) UsuarioService.class, "especialidadeService", (Object) especialidadeService0);
      Vector<Usuario> vector0 = new Vector<Usuario>();
      UsuarioRepository usuarioRepository0 = mock(UsuarioRepository.class, new ViolatedAssumptionAnswer());
      doReturn(vector0).when(usuarioRepository0).findAll();
      Injector.inject(usuarioService0, (Class<?>) UsuarioService.class, "usuarioRepository", (Object) usuarioRepository0);
      Injector.validateBean(usuarioService0, (Class<?>) UsuarioService.class);
      Injector.inject(usuarioController0, (Class<?>) UsuarioController.class, "usuarioService", (Object) usuarioService0);
      Injector.validateBean(usuarioController0, (Class<?>) UsuarioController.class);
      ResponseEntity<Response> responseEntity0 = usuarioController0.listaFornecedores();
      assertEquals(200, responseEntity0.getStatusCodeValue());
  }
}
