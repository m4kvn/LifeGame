# LifeGame

![](https://www.evernote.com/l/As2q2r6usLNOEo1P-oSG5n0HZwGKwks74WYB/image.png)

## ライフゲームに関する説明

　ライフゲームとは、生命の誕生・進化・淘汰などのプロセスを再現するシミュレーションゲームであり、1970年にイギリスの数学者「ジョン・ホートン・コンウェイ」によって考案された。このライフゲームでは、生物集団においての「集団が過疎でも過密でも個体の生存に適さない」という個体群生態学的な側面を、「生」と「死」の状態のルールを決めてシュミレーションを行う。

## 使用したルールに関する説明

　今回は、生物集団における個体をブロックとし、以下のルールを使用する。

| 状態 | ルール |
| :--: | :--- |
| 誕生 | 死んでいるブロックに隣接する生きたブロックがちょうど3つあれば、次の世代が誕生する |
| 生存 | 生きているブロックに隣接する生きたブロックが2つか3つならば、次の世代でも生存する |
| 過疎 | 生きているブロックに隣接する生きたブロックが1つ以下ならば、過疎により死滅する |
| 過密 | 生きているブロックに隣接する生きたブロックが4つ以上ならば、過密により死滅する |

## 自分で興味を持った初期パターンとその変化についての説明

![](https://www.evernote.com/l/As2v0zb4ws5Japs3LCQBsWmiUybfm2i8Mh4B/image.png)

## プログラム作成上工夫した点

- 集団の状態を途中で確認できるように、一時停止機能をつけた
- 一時停止中にも個体の状態を変化させられようにした
- 最初からやり直すために、初期化機能をつけた
- ProcessingをSwing上で動くようにし、操作を行うボタンを配置した

## 参考にした情報

> - [ニコニコ動画 ライフゲームの世界１【複雑系】](http://www.nicovideo.jp/watch/sm19347846)
> - [Wikipedia ライフゲーム](https://ja.wikipedia.org/wiki/%E3%83%A9%E3%82%A4%E3%83%95%E3%82%B2%E3%83%BC%E3%83%A0)