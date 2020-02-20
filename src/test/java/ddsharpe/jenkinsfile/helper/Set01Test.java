package ddsharpe.jenkinsfile.helper;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("unit")
class Set01Test {

  @Test
  void test01() throws Exception {
    assertEquals(2, 1 + 1);
  }

  @Tag("failing")
  @Test
  void test02() throws Exception {
    assertEquals(2, 3);
  }

  @Test
  void test03() throws Exception {
    assertEquals(2, 1 + 1);
  }

  @Test
  void test04() throws Exception {
    assertEquals(2, 1 + 1);
  }

  @Test
  void test05() throws Exception {
    assertEquals(2, 1 + 1);
  }

}
