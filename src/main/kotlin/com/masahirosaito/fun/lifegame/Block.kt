package com.masahirosaito.`fun`.lifegame

import processing.core.PApplet

/**
 * Block
 *
 * ライフゲームの一つ一つのブロック
 *
 * @property pApplet Processing Appletインスタンス
 * @property x ブロックの配列インデックスx
 * @property y ブロックの配列インデックスy
 * @property size ブロックの一片の大きさ
 * @property isAlive ブロックが生きているかどうか
 * @property preAlive ブロックが次の世代に生きているかどうか
 * @property drawX ブロックを描写するx座標
 * @property drawY ブロックを描写するy座標
 * @property relatives ブロックに隣接するブロックのリスト
 * @constructor 死亡状態のブロックを生成する
 */
data class Block(
        val pApplet: PApplet,
        val x: Int,
        val y: Int,
        val size: Float,
        var isAlive: Boolean = false,
        var preAlive: Boolean = false

) {
    val drawX = x * size
    val drawY = y * size
    val relatives = mutableSetOf<Block>()

    /**
     * ブロックを描画する
     *
     * ブロックが生きている場合は、緑色
     * ブロックが死んでいる場合は、黒色
     */
    fun draw() {
        pApplet.fill(0f, if (isAlive) 255f else 0f, 0f)
        pApplet.rect(drawX, drawY, size, size)
    }

    /**
     * ブロックに隣接する生きているブロック数を数える
     */
    fun getRelativesAliveNum(): Int = relatives.filter { it.isAlive }.size

    /**
     * ブロックの世代を進める
     */
    fun nextGeneration() { isAlive = preAlive }

    /**
     * ブロックの次の世代を計算する
     */
    fun nextPreGeneration() {
        getRelativesAliveNum().apply {
            preAlive = if (isAlive) this == 2 || this == 3 else this == 3
        }
    }

    /**
     * ブロックを初期化する
     */
    fun reset() {
        isAlive = false
        preAlive = false
    }

    /**
     * ブロックに隣接するブロックを確保する
     */
    fun setRelativeBlocks(blocks: Array<Array<Block>>, lineBlockNum: Int) {
        for (i in x-1..x+1) for (j in y-1..y+1) {
            when {
                i == x && j == y -> {}
                i < 0 || lineBlockNum <= i -> {}
                j < 0 || lineBlockNum <= j -> {}
                else -> relatives.add(blocks[i][j])
            }
        }
    }
}