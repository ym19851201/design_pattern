package abstractfactory;

public class FactoryMethod {
  public Alphabet createAlphabet(String ch) {
    if (ch.equals("a")) {
      return new A();
    } else if (ch.equals("b")) {
      return new B();
    } else if (ch.equals("c")) {
      return new C();
    }

    return null;
  }

  public static abstract class Alphabet {
    public abstract String whoAmI();
  }

  public static class A extends Alphabet {
    public String whoAmI() {
      return "A";
    }
  }

  public static class B extends Alphabet {
    public String whoAmI() {
      return "B";
    }
  }

  public static class C extends Alphabet {
    public String whoAmI() {
      return "C";
    }
  }
}
