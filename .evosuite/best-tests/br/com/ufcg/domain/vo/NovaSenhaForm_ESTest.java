/*
 * This file was automatically generated by EvoSuite
 * Mon Nov 12 04:07:30 GMT 2018
 */

package br.com.ufcg.domain.vo;

import org.junit.Test;
import static org.junit.Assert.*;
import br.com.ufcg.domain.vo.NovaSenhaForm;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class NovaSenhaForm_ESTest extends NovaSenhaForm_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      NovaSenhaForm novaSenhaForm0 = new NovaSenhaForm("[R.f/", "TE]", "");
      String string0 = novaSenhaForm0.getSenhaNova();
      assertEquals("", novaSenhaForm0.getConfirmacao());
      assertEquals("TE]", string0);
      assertEquals("[R.f/", novaSenhaForm0.getSenhaAntiga());
  }

  @Test(timeout = 4000)
  public void test1()  throws Throwable  {
      NovaSenhaForm novaSenhaForm0 = new NovaSenhaForm("[R.f/", "TE]", "");
      assertEquals("TE]", novaSenhaForm0.getSenhaNova());
      
      novaSenhaForm0.setSenhaNova("");
      novaSenhaForm0.getSenhaNova();
      assertEquals("[R.f/", novaSenhaForm0.getSenhaAntiga());
  }

  @Test(timeout = 4000)
  public void test2()  throws Throwable  {
      NovaSenhaForm novaSenhaForm0 = new NovaSenhaForm("[R.f/", "TE]", "");
      String string0 = novaSenhaForm0.getSenhaAntiga();
      assertEquals("TE]", novaSenhaForm0.getSenhaNova());
      assertEquals("", novaSenhaForm0.getConfirmacao());
      assertEquals("[R.f/", string0);
  }

  @Test(timeout = 4000)
  public void test3()  throws Throwable  {
      NovaSenhaForm novaSenhaForm0 = new NovaSenhaForm();
      novaSenhaForm0.setSenhaAntiga("");
      String string0 = novaSenhaForm0.getSenhaAntiga();
      assertEquals("", string0);
  }

  @Test(timeout = 4000)
  public void test4()  throws Throwable  {
      NovaSenhaForm novaSenhaForm0 = new NovaSenhaForm();
      String string0 = novaSenhaForm0.getConfirmacao();
      assertNull(string0);
  }

  @Test(timeout = 4000)
  public void test5()  throws Throwable  {
      NovaSenhaForm novaSenhaForm0 = new NovaSenhaForm("[R.f/", "TE]", "");
      String string0 = novaSenhaForm0.getConfirmacao();
      assertEquals("", string0);
      assertEquals("TE]", novaSenhaForm0.getSenhaNova());
      assertEquals("[R.f/", novaSenhaForm0.getSenhaAntiga());
  }

  @Test(timeout = 4000)
  public void test6()  throws Throwable  {
      NovaSenhaForm novaSenhaForm0 = new NovaSenhaForm();
      String string0 = novaSenhaForm0.getSenhaNova();
      assertNull(string0);
  }

  @Test(timeout = 4000)
  public void test7()  throws Throwable  {
      NovaSenhaForm novaSenhaForm0 = new NovaSenhaForm();
      String string0 = novaSenhaForm0.getSenhaAntiga();
      assertNull(string0);
  }

  @Test(timeout = 4000)
  public void test8()  throws Throwable  {
      NovaSenhaForm novaSenhaForm0 = new NovaSenhaForm();
      novaSenhaForm0.setConfirmacao("");
      assertNull(novaSenhaForm0.getSenhaAntiga());
  }

  @Test(timeout = 4000)
  public void test9()  throws Throwable  {
      NovaSenhaForm novaSenhaForm0 = new NovaSenhaForm("", "", "@?1Bwj21+s");
      String string0 = novaSenhaForm0.getConfirmacao();
      assertEquals("", novaSenhaForm0.getSenhaNova());
      assertEquals("", novaSenhaForm0.getSenhaAntiga());
      assertEquals("@?1Bwj21+s", string0);
  }
}
