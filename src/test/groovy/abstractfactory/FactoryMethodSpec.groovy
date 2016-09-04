package abstractfactory

import spock.lang.Specification

class FactoryMethodSpec extends Specification {
  def 'FactoryMethod instance can produce precise Alphabet instance'() {
    setup:
    def sut = new FactoryMethod()
    when:
    def product = sut.createAlphabet ch
    then:
    product.whoAmI() == whoAmI

    where:
    ch |whoAmI
    'a'|'A'
    'b'|'B'
    'c'|'C'
  }
}
