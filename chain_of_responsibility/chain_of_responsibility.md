class: center, middle, inverse

# デザインパターン再学習
## Chain of responsibilityの章

---

class: center, middle

# Chain of responsibilityとは？

---
class: center, middle, inverse

# 役所


---

.left-column[
## Chain of responsi bilityとは？
]

.right-column[
```java
while(true) {
    Problem problem = miyazaki.○○したいんですけど();
    if (部署.acceptable(problem)) {
        公務員.handle(problem);
        break;
    } else {
        今までに行かされた部署.add(部署);
        部署 = 公務員.他の部署に丸投げ();
    }

    if (今までに行かされた部署.indexOf(部署) > 0) {
        miyazaki.attack(公務員);
    }
}
```
]

---

.left-column[
## Chain of responsi bilityとは？
]

.right-column[
## まあこんな感じ(attackは除き)
]

--
.right-column[
## ifやswitchは消えるよね
]

--
.right-column[
## 末尾の人が問題を処理することが多い場合、当然のように処理にかかる時間が増えていく
]
