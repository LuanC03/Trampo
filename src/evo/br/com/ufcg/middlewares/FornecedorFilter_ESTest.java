/*
 * This file was automatically generated by EvoSuite
 * Mon Nov 12 04:03:06 GMT 2018
 */

package br.com.ufcg.middlewares;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import br.com.ufcg.middlewares.FornecedorFilter;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class FornecedorFilter_ESTest extends FornecedorFilter_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      FornecedorFilter fornecedorFilter0 = null;
      try {
        fornecedorFilter0 = new FornecedorFilter();
        fail("Expecting exception: NoClassDefFoundError");
      
      } catch(NoClassDefFoundError e) {
         //
         // Could not initialize class org.apache.commons.logging.LogFactory$Log4jLog
         //
         verifyException("org.apache.commons.logging.LogFactory$Log4jDelegate", e);
      }
  }
}
