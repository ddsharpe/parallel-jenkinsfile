package ddsharpe.jenkinsfile.helper;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("SetB")
class SetB1Test {
  private final long DURATION = 2000;

  @Test
  void test01() throws Exception {
    Thread.sleep(DURATION);
    assertEquals(2, 1 + 1);
  }

  @Test
  void test02() throws Exception {
    Thread.sleep(DURATION);
    assertEquals(2, 1 + 1);
  }

  @Test
  void test03() throws Exception {
    Thread.sleep(DURATION);
    assertEquals(2, 1 + 1);
  }

  @Test
  void test04() throws Exception {
    Thread.sleep(DURATION);
    assertEquals(2, 1 + 1);
  }

  @Test
  void test05() throws Exception {
    Thread.sleep(DURATION);
    assertEquals(2, 1 + 1);
  }

}
