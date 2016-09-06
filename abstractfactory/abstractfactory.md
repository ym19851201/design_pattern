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
    public void growVegetable() {
        Tomato tomato = new Tomato();
        plant(tomato);
        water(tomato);
        harvest(tomato);
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
    public void growVegetable() {
        Vegetable vegetable = createVegetable();
        plant(vegetable);
        water(vegetable);
        harvest(vegetable);
    }

    protected abstract void plant(Vegetable vegetable);
    protected abstract void water(Vegetable vegetable);
    protected abstract void harvest(Vegetable vegetable);
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
public class PotatoFarmer {
    @Override
    protected Vegetable createVegetable() {
        return new Potato();
    }

    @Override
    protected abstract void plant(Vegetable vegetable) {
        // 芋なりの植え方
    }
    @Override
    protected abstract void water(Vegetable vegetable) {
        // 芋なりの水やり
    }
    @Override
    protected abstract void harvest(Vegetable vegetable) {
        // 芋なりの収穫方法
    }
}
```
## growVegetable()は親クラスで実装済みなので不要
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
public void growVegetable() {
    Vegetable vegetable = createVegetable();
    plant(vegetable);
    water(vegetable);
    harvest(vegetable);
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
+ 喜べもうちょい柔軟だ
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

        Product product = nikomu(material1, material2);

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
public abstract class AlchmeyFactory {
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

        Product product = nikomu(material1, material2);

        return Product;
    }
}
```

---
class: center, middle, inverse

# 以上！
