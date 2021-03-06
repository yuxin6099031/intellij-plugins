package org.jetbrains.plugins.cucumber.java.resolve;

import com.intellij.testFramework.LightProjectDescriptor;
import org.jetbrains.plugins.cucumber.java.CucumberJavaTestUtil;

public class CucumberJavaTestResolveTest extends BaseCucumberJavaResolveTest {
  public void testNavigationFromStepToStepDef01() {
    doTest("stepResolve_01", "I p<caret>ay 25", "i_pay");
  }
  public void testNavigationFromStepToStepDef02() {
    doTest("stepResolve_01", "the followi<caret>ng groceries", "the_following_groceries");
  }
  public void testNavigationFromStepToStepDef03() {
    doTest("stepResolve_01", "my change sh<caret>ould be 4", "my_change_should_be_");
  }

  public void testNavigationWithQuotes01() {
    doTest("stepResolve_02", "I subtract 5 fr<caret>om 9", "I_subtract_from");
  }

  public void testNavigationWithQuotes02() {
    doTest("stepResolve_02", "the resu<caret>lt is 4", "the_result_is");
  }

  public void testNavigationWithQuotes03() {
    doTest("stepResolve_02", "tes<caret>t \"test\"", "test");
  }

  public void testWordSymbolWithUnicode() {
    doTest("stepResolve_w", "пласт<caret>ик", null);
  }

  public void testWordSymbolWithAZ() {
    doTest("stepResolve_w", "plast<caret>ic", "payment_mode");
  }

  public void testJava8StepDef() {
    doTest("stepResolve_java8", "I have cuk<caret>es", "Given");
  }

  @Override
  protected LightProjectDescriptor getProjectDescriptor() {
    return CucumberJavaTestUtil.createCucumberJava8ProjectDescriptor();
  }
}
