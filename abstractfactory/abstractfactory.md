class: center, middle, inverse

# デザインパターン再学習
## Abstract Factoryの章

---

# はじめに

.left-column[
## ﾃﾞｻﾞﾊﾟﾀって?
]

.right-column[
+ GoFが提唱
]

--

.right-column[
+ OOPにおけるクラス(インスタンス)のうまい使い方
]

--

.right-column[
+ ぶっちゃけ真似しないでもいいと思う
]

--

.right-column[
+ エッセンスだけ獲得できれば
]


---

class: center, middle, inverse

# 以上を踏まえつつ

---

class: center, middle

# Abstract Factoryとは？

---

class: center, middle, inverse

# の前に

---

class: center, middle

# Factory Methodとは？

---

.left-column[
## Factory Methodとは？
]

--

.right-column[
+ やっぱりﾃﾞｻﾞﾊﾟﾀのひとつ
]

--

.right-column[
+ 名前似ててムカつく
]

--

.right-column[
+ でも名前が似てる以上共通部分も多い
]
--
.right-column[
+ つまり先にやっとくと説明しやすいんですよ
]

---
.left-column[
## Factory Methodとは？
## Code
]

--

.right-column[
## かめはめ波撃ちたい
]

--

.right-column[
```java
public class KamehamehaAction {
    public void shoot() {
        Goku goku = new Goku();
        goku.kame();
        goku.hame();
        goku.ha();
    }
}
```
]

--
.right-column[
＿人人人人人人人人人人＿  
＞　悟空しか撃てない　＜  
￣Y^Y^Y^Y^Y^Y^Y^Y^Y￣  
]

---
.left-column[
## Factory Methodとは？
## Code
]
.right-column[
## そこでまずは
]

.right-column[
```java
public abstract class KamehamehaShooter {
    abstract protected void kame();
    abstract protected void hame();
    abstract protected void ha();
}
```
]

--

.right-column[
```java
public abstract class KamehamehaAction {
    protected abstract KamehamehaShooter createShooter();
    public void shoot() {
        KamehamehaShooter shooter = createShooter();
        shooter.kame();
        shooter.hame();
        shooter.ha();
    }
}
```
]

---
.left-column[
## Factory Methodとは？
## Code
]
.right-column[
## そして
]

.right-column[
```java
public class Yamucha extends KamehamehaShooter {
    @Override
    protected void kame() {
        ｼｮﾎﾞｲ;
    }

    @Override
    protected void hame() {
        ﾋﾞﾐｮｳ;
    }

    @Override
    protected void ha() {
        ﾖﾜｲ;
    }
}
```
]

---
.left-column[
## Factory Methodとは？
## Code
]
.right-column[
## そして
]

.right-column[
```java
public class YamuchaKamehamehaAction {
    @Override
    protected KamehamehaShooter createShooter() {
        return new Yamucha();
    }
}
```
## shoot()は親クラスで実装済みなので不要
]

---
.left-column[
## Factory Methodとは？
## Code
## つまり
]

--
.right-column[
ヤムチャだろうがセルだろうが、かめはめ波ユーザを実装、ユーザを作成し利用するクラスを実装すればまあどんなキャラでもかめはめ波を撃たせることができますね
]

--
.right-column[
こいつがインスタンスを生成するFactory **Method**
```java
abstract protected KamehamehaShooter createShooter();
    ```
]

--
.right-column[
Factory(工場)とか宣うから、何か色々作れそうな予感がするけどこの程度です。ただのメソッドです  
ぶっちゃけ微妙。ってかこのぐらい無意識にやってる。
]

---
.left-column[
## Factory Methodとは？
## Code
## つまり
]
.right-column[
敢えて言うならTemplate Methodﾊﾟﾃｨｰﾝと相性がよいっていうかよく一緒に使われる
]

--
.right-column[
これね

```java
public void shoot() {
    KamehamehaShooter shooter = createShooter();
    shooter.kame();
    shooter.hame();
    shooter.ha();
}
    ```
]

---

class: center, middle, inverse
長くなりましたね

---

class: center, middle, inverse
# では本題

---
.left-column[
## Abstract Factoryとは？
]

--
.right-column[
+ やっぱりインスタンス生成に関わるﾊﾟﾃｨｰﾝ
]

--
.right-column[
+ 喜べもうちょい柔軟だ
]

---
.left-column[
## Factory Methodとは？
## Code
]

--
.right-column[
## フュージョンしたい
]

--
.right-column[
```java
public class FusionAction {
    public Zsenshi fusion() {
        Zsenshi zsenshi1 = new Goten();
        Zsenshi zsenshi2 = new Trunks();

        fyu(zsenshi1, zsenshi2);
        jon(zsenshi1, zsenshi2);
        Zsenshi gotenkusu = ha(zsenshi1, zsenshi2);

        return gotenkusu;
    }
}
```
]

---
.left-column[
## Factory Methodとは？
## Code
]

.right-column[
## これだとゴテンクスしか使えない
## 地球の危機に際してミスター・サタンを混ぜる可能性があるのは危険
]

---
.left-column[
## Factory Methodとは？
## Code
]

.right-column[
```java
public abstract class FusionFactory {
    public abstract Zsenshi createZsenshi1();
    public abstract Zsenshi createZsenshi2();
}
```

```java
public class GojitaFactory extends FusionFactory {
    @Override
    public Zsenshi createZsenshi1() {
        return new Goku();
    }
    
    @Override
    public Zsenshi createZsenshi2() {
        return new Bejita();
    }
}
```
]

---
.left-column[
## Factory Methodとは？
## Code
]

.right-column[
```java
public class FusionAction {
    public Zsenshi fusion(FusionFactory factory) {
        Zsenshi zsenshi1 = factory.createZsenshi1();
        Zsenshi zsenshi2 = factory.createZsenshi2();

        fyu(zsenshi1, zsenshi2);
        jon(zsenshi1, zsenshi2);
        Zsenshi fusion = ha(zsenshi1, zsenshi2);

        return fusion;
    }
}
```
]

---
.left-column[
## Factory Methodとは？
## Code
## つまり
]

.right-column[
+ Factory Methodだと1クラスにつき1個の型と、生成されるインスタンスの型が固定されていた
]

--
.right-column[
+ Abstract Factoryではインスタンスの生成の抽象度を1つ上げた感じ
]

--
.right-column[
+ fusion(factory)のfactory引数により、柔軟にインスタンスを生成できる  
]
--
.right-column[
+ インスタンス生成が複雑なとき、間違いが起きないようにすることも
]

---
.left-column[
## Factory Methodとは？
## Code
## つまり
## さらに
]

.right-column[
### miyazakiならこうするかも?
]

---

```java
public abstract class FusionFactory {
    private static String PACKAGE_STR = "package.includes.fusionfactories.";

    public static FusionFactory createFactory(FactoryEnum factoryEnum) {
        String fqcn = PACKAGE_STR + factoryEnum.toString();
        Class clazz = Class.forName(fqcn);

        return (FusionFactory) clazz.newInstance();
    }

    public abstract Zsenshi createZsenshi1();
    public abstract Zsenshi createZsenshi2();
}
```

```java
public class FusionAction {
    public Zsenshi fusion(FactoryEnum factoryEnum) {
        FusionFactory factory = FusionFactory.createFactory(factoryEnum);
        Zsenshi zsenshi1 = factory.createZsenshi1();
        Zsenshi zsenshi2 = factory.createZsenshi2();

        fyu(zsenshi1, zsenshi2);
        jon(zsenshi1, zsenshi2);
        Zsenshi fusion = ha(zsenshi1, zsenshi2);

        return fusion;
    }
}
```

---
class: center, middle, inverse

# 以上！
