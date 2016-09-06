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
## ~~かめはめ波撃ちたい~~
## 野菜育てるゲーム
]

--

.right-column[
```java
public class Farmer {
    public Vegtable createVegetable() {
        Tomato tomato = new Tomato();
        return tomato;
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
## ~~かめはめ波撃ちたい~~
## 野菜育てるゲーム
]

.right-column[
＿人人人人人人人人人人人＿  
＞　トマトしか穫れねえ　＜  
￣Y^Y^Y^Y^Y^Y^Y^Y^Y^Y￣  
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
public abstract class Vegetable {
    private double weight;
    private Color color;
    .
    .
    .
}
```
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
public abstract class Farmer {
    protected abstract Vegetable createVegetable();
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
public class Potato extends Vegetable {
}
```
```java
public class PotatoFarmer {
    @Override
    protected Vegetable createVegetable() {
        return new Potato();
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

--
.right-column[
~~ヤムチャだろうがセルだろうが、かめはめ波ユーザを実装、ユーザを作成し利用するクラスを実装すればまあどんなキャラでもかめはめ波を撃たせることができますね~~  
芋だろうがトマトだろうが、野菜クラスをいっぱい作って、農家にそのインスタンスを生成させるメソッドを実装すれば、何農家だろうが作れますね  
実際はオブジェクトの生成を実装から切り離すことが目的っぽい
]

--
.right-column[
こいつがインスタンスを生成するFactory **Method**
```java
protected abstract Vegetable createVegetable();
    ```
]

--
.right-column[
Factory(工場)とか宣うから、何か色々作れそうな予感がするけどこの程度です。ただのメソッドです  
ぶっちゃけ微妙。ってかこのぐらい無意識にやりそう。
]

---
.left-column[
## Factory Methodとは？
## Code
## つまり
]
.right-column[
敢えて言うならTemplate Methodﾊﾟﾃｨｰﾝと相性がよいっていうかよく一緒に使われるのかもね
]

--
.right-column[
これね

```java
public Vegetable createVegetable() {
    Vegetable vegetable = createVegetable();
    plant(vegetable);
    water(vegetable);
    harvest(vegetable);

    return vegetable;
}

protected void plant(Tomato tomato) {
    // 植える
}
protected void water(Tomato tomato) {
    // 水やる
}
protected void harvest(Tomato tomato) {
    // 収穫する
}
```
]

--
.right-column[
野菜を育てるという一連の処理は実装しておいて、具体的な処理は継承するクラスに任せる。  
特に今回の場合、一連の処理の中に野菜インスタンスを生成するというFactory Methodが入っていた。
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
+ ~~喜べもうちょい柔軟だ~~
+ そんなこと思ってた俺フ○○ク
]

---
.left-column[
## Abstract Factoryとは？
## Code
]

--
.right-column[
## ~~あなたとフュージョンしたい~~
## ~~○○○のアトリエ~~
## 錬金術のゲーム作るよ！
]

--
.right-column[
```java
public class Alchemist {
    public Product synthesize() {
        Material mandoragora = new Mandragora();
        Material mushroom = new Mushroom();

        Product portion = nikomu(mandoragora, mushroom);

        return portion;
    }
}
```
]

---
.left-column[
## Abstract Factoryとは？
## Code
]

.right-column[
## ~~これだとゴテンクスしか使えない~~
## ~~地球の危機に際してミスター・サタンを混ぜる可能性があるのは危険~~
## ポーションしか作れない
## 色々作りたいですよね
]

---
.left-column[
## Abstract Factoryとは？
## Code
]

.right-column[
```java
public abstract class AlchemyFactory {
    // 正直Factory Method
    public abstract Material createMaterial1();
    public abstract Material createMaterial2();
}
```

```java
public class HomunculusFactory extends AlchemyFactory {
    @Override
    public Material createMaterial1() {
        return new Blood();
    }
    
    @Override
    public Material createMaterial2() {
        return new WaterOfLife();
    }
}
```
]

---
.left-column[
## Abstract Factoryとは？
## Code
]

.right-column[
```java
public class Alchemist {
    public Product synthesize(AlchemyFactory factory) {
        Material material1 = factory.createMaterial1();
        Material material2 = factory.createMaterial2();

        Product product = factory.synthesize(material1, material2);

        return product;
    }
}
```
]

---
.left-column[
## Abstract Factoryとは？
## Code
## つまり
]

.right-column[
+ 単純に見てるレイヤーが違う
]

--
.right-column[
+ さっきの例でいうPotatoFarmerもHomunculusFactoryもやってることはFactory Methodによるインスタンスの生成
]

--
.right-column[
+ Alchemistから見たら、様々なAlchemyFactoryを使って(任せて)色んなもの生成できる(Abstract)
]
--
.right-column[
+ その関係性がAbstract Factory
]

--
.right-column[
+ それを実現するのにFactory Methodが使われる(こともある)ってだけ
]

---
.left-column[
## Abstract Factoryとは？
## Code
## つまり
## さらに
]

.right-column[
### miyazakiならこうするかも?
]

---

```java
public abstract class AlchemyFactory {
    private static String PACKAGE_STR = "package.includes.alchemyfactories.";

    public static AlchemyFactory createFactory(FactoryEnum factoryEnum) {
        String fqcn = PACKAGE_STR + factoryEnum.toString();
        Class clazz = Class.forName(fqcn);

        return (AlchemyFactory) clazz.newInstance();
    }

    public abstract Material createMaterial1();
    public abstract Material createMaterial2();
}
```

```java
public class Alchemist {
    public Product synthesize(FactoryEnum factoryEnum) {
        FusionFactory factory = FusionFactory.createFactory(factoryEnum);
        Material material1 = factory.createMaterial1();
        Material material2 = factory.createMaterial2();

        Product product = factory.synthesize(material1, material2);

        return Product;
    }
}
```

---
class: center, middle, inverse

# 以上！
