package ddsharpe.jenkinsfile.helper;

    import org.junit.jupiter.api.Test;

    import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelloWorldTest {
  @Test
  void testGetMessage() {
    HelloWorld t = new HelloWorld();
    assertEquals("Hello", t.getMessage(), "Unable to get message");
  }
}
