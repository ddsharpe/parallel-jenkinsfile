package ddsharpe.jenkinsfile.helper;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("SetC")
class SetC1Test {
  private final long DURATION = 200;

  @Test
  void test01() throws Exception {
    Thread.sleep(DURATION);
    assertEquals("test", "test", "Failed string compare");
  }

  @Test
  void test02() throws Exception {
    Thread.sleep(DURATION);
    assertEquals("test", "test", "Failed string compare");
  }

  @Test
  void test03() throws Exception {
    Thread.sleep(DURATION);
    assertEquals("test", "test", "Failed string compare");
  }

  @Test
  void test04() throws Exception {
    Thread.sleep(DURATION);
    assertEquals("test", "test", "Failed string compare");
  }

  @Test
  void test05() throws Exception {
    Thread.sleep(DURATION);
    assertEquals("test", "test", "Failed string compare");
  }

}
