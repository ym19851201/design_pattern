class: center, middle, inverse

# デザインパターン再学習
## Builderの章

---

class: center, middle

# Builderとは？

---

.left-column[
## Builderとは？
]

--

.right-column[
+ インスタンス生成シリーズ 完成していたの・・・！？
]

--

.right-column[
+ 完成(全部やりきる)までいけたらいいですね
]

---
class: center, middle

# Builderの何たるかだいたい皆さん知ってますよね？

---
class: center, middle

![king](https://iwiz-chie.c.yimg.jp/im_siggtZzMHsC4QIZEW4mu4Bs3tw---exp5m-n1/d/iwiz-chie/note-137262-i3-img.JPEG)

---
.left-column[
## Factory Methodとは？
## Code
]

.right-column[
```java
Hoge hoge = new Hoge("ABC", "XYZ", 3, "hoge", 5);
```
]

--

.right-column[
![nani](http://serif.hatelabo.jp/images/original/5586bbd8ce592d117898ad1083207f56128c528c.gif?1190545946)
]

---
.left-column[
## Factory Methodとは？
## Code
]

.right-column[
必須ではないオプションって結構あるじゃないですか
]

--
.right-column[
```java
Fuga fuga = new Fuga(null, 5.0d, null, hoge, null, null);
```
]

--

.right-column[
![nani](http://serif.hatelabo.jp/images/original/5586bbd8ce592d117898ad1083207f56128c528c.gif?1190545946)
]

---
.left-column[
## Factory Methodとは？
## Code
### GoFの場合
]

.right-column[
```java
public class Human {
    // 必須
    private String name;
    // 必須
    private int age;
    // オプション
    private String nationality;

    //getters, setters...
}
```
]

---
.left-column[
## Factory Methodとは？
## Code
### GoFの場合
]

.right-column[
```java
public class Director {
    private Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    public void construct() {
        builder.name("Diablo");
        builder.age(45);
        builder.nationality("Italy");
    }
}
```
]

---
.left-column[
## Factory Methodとは？
## Code
### GoFの場合
]

.right-column[
```java
public interface Builder {
    public void name(String name);
    public void age(int age);
    public nationality(String nationality);

    public Human getResult();
}
```
]

---
.left-column[
## Factory Methodとは？
## Code
### GoFの場合
]

.right-column[
```java
class NormalBuilder implements Builder {
    private Human human;
    
    public NormalBuilder(Human human) {
        this.human = human;
    }

    @Override
    public void name(String name) {
        human.setName(name);
    }
    @Override
    public void age(int age) {
        human.setAge(age);
    }
    @Override
    public nationality(String nationality) {
        human.setNationality(nationality);
    }

    @Override
    public Human getResult() {
        if(human.getName() == null || human.getAge() == null) {
            throw new NullPointerException();
        }

        return this.human;
    }
}
```
]

---
.left-column[
## Factory Methodとは？
## Code
### GoFの場合
]

.right-column[
+ 生成されるオブジェクト(People)と生成ロジック(Builder)が分離され、PeopleがBuilderを知らなくていい。
]
.right-column[
+ Builderの実装クラスを切り替えることで、生成するPeopleの制御を出来る。
]
.right-column[
+ interfaceや実装など、記述量が多くなる。
]
.right-column[
+ 生成処理が他のBuilderと比べると複雑。
]

---
.left-column[
## Factory Methodとは？
## Code
### GoFの場合
]

.right-column[
+ まあね。。。
]
.right-column[
+ Builderの実装クラスを切り替えることで、生成するPeopleの制御を出来る。
]
.right-column[
+ interfaceや実装など、記述量が多くなる。
]
.right-column[
+ 生成処理が他のBuilderと比べると複雑。
]

---
.left-column[
## Factory Methodとは？
## Code
### GoFの場合
]

.right-column[
+ まあね。。。
]
.right-column[
+ そうね。。。
]
.right-column[
+ interfaceや実装など、記述量が多くなる。
]
.right-column[
+ 生成処理が他のBuilderと比べると複雑。
]

---
.left-column[
## Factory Methodとは？
## Code
### GoFの場合
]

.right-column[
+ まあね。。。
]
.right-column[
+ そうね。。。
]
.right-column[
+ めんどい
]
.right-column[
+ 生成処理が他のBuilderと比べると複雑。
]

---
.left-column[
## Factory Methodとは？
## Code
### GoFの場合
]

.right-column[
+ まあね。。。
]
.right-column[
+ そうね。。。
]
.right-column[
+ めんどい
]
.right-column[
+ ウザい
]

---
.left-column[
## Factory Methodとは？
## Code
### GoFの場合
### Effective Javaの場合
]

.right-column[
```java
class Human {
    private String name;
    private Integer age;
    private String nationality;

    private Human(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.nationality = builder.nationality;
    }

    //続く...
```
]

---
.left-column[
## Factory Methodとは？
## Code
### GoFの場合
### Effective Javaの場合
]

.right-column[
```java
    //続き...
    static class Builder {
        private String name;
        private Integer age;
        private String hobby;

        Builder(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        Builder hobby(String hobby) {
            this.hobby = hobby;
            return this;
        }

        People build() {
            if (name == null || age == null) {
                throw new NullPointerException();
            }
            return new People(this);
        }
    }
}
```
]

---
.left-column[
## Factory Methodとは？
## Code
### GoFの場合
### Effective Javaの場合
]

.right-column[
```java
new Human.Builder("Diablo", 45).nationality("Italy").build();
```
]

---
.left-column[
## Factory Methodとは？
## Code
### GoFの場合
### Effective Javaの場合
]

.right-column[
+ 流れるインターフェイスでオブジェクトの生成が可能。
]
.right-column[
+ GoF Builderと比べ少ない記述量でオブジェクトの生成ができる。
]
.right-column[
+ 生成ロジック(Builder)とオブジェクト(People)の分離ができない。
]

---
.left-column[
## Factory Methodとは？
## Code
### GoFの場合
### Effective Javaの場合
]

.right-column[
+ メソッドチェイン感！
]
.right-column[
+ GoF Builderと比べ少ない記述量でオブジェクトの生成ができる。
]
.right-column[
+ 生成ロジック(Builder)とオブジェクト(People)の分離ができない。
]

---
.left-column[
## Factory Methodとは？
## Code
### GoFの場合
### Effective Javaの場合
]

.right-column[
+ メソッドチェイン感！
]
.right-column[
+ もう何もダルくない！！
]
.right-column[
+ 生成ロジック(Builder)とオブジェクト(People)の分離ができない。
]

---
.left-column[
## Factory Methodとは？
## Code
### GoFの場合
### Effective Javaの場合
]

.right-column[
+ メソッドチェイン感！
]
.right-column[
+ もう何もダルくない！！
]
.right-column[
+ もうこの際いいんじゃないかな。。。
]

---
.left-column[
## Factory Methodとは？
## Code
### GoFの場合
### Effective Javaの場合
### Lombok Builderの場合
]

.right-column[
```java

@Builder
class Human {
    @NonNull
    private String name;
    @NonNull
    private Integer age;
    private String nationality;

}
```
]
