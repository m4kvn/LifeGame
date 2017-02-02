package com.masahirosaito.`fun`.lifegame

import processing.core.PApplet
import processing.core.PConstants

/**
 * LifeGame Processing Applet
 *
 * @property windowSize ウィンドウのサイズ
 * @property blockSize ブロックのサイズ
 * @property lineBlockNum 一辺のブロック数
 * @property isStart ライブゲームがスタートされているかどうか
 * @property blocks 全てのブロックを格納した配列
 * @constructor ライフゲームを生成する
 */
class LifeGame : PApplet() {
    val windowSize = 500
    val blockSize = 10
    val lineBlockNum = windowSize / blockSize
    var isStart = false

    val blocks = Array(lineBlockNum, { x ->
        Array(lineBlockNum, { y ->
            Block(this, x, y, blockSize.toFloat())
        })
    })

    /**
     * 初期設定
     *
     * 1. ゲームのフレームレートを10に設定
     * 2. ウィンドウのサイズを設定
     * 3. 背景を黒に設定
     * 4. 線の太さを50設定
     * 5. 全てのブロックに隣接するブロックを設定する
     */
    override fun setup() {
        frameRate(10f)
        size(windowSize, windowSize)
        background(0)
        stroke(50)

        setRelativeBlocks()
    }

    /**
     * 描画処理
     *
     * 1. 背景を黒で塗りつぶす
     * 2. ゲームが開始している場合は、全てのブロックの次の世代を計算
     * 3. ゲームが開始している場合は、全てのブロックの次の世代を変更
     * 4. 全てのブロックを描画する
     */
    override fun draw() {
        background(0)

        if (isStart) {
            nextPreGeneration()
            nextGeneration()
        }

        drawBlocks()
    }

    /**
     * 全てのブロックを描画する
     */
    fun drawBlocks() {
        blocks.forEach { it.forEach(Block::draw) }
    }

    /**
     * マウスをクリックした時の処理
     *
     * 左クリックは、ブロックを誕生させる
     * 右クリックは、ブロックを死亡させる
     */
    override fun mouseClicked() {
        beAlive(mouseX / blockSize, mouseY / blockSize)
    }

    /**
     * マウスをドラッグした時の処理
     *
     * ドラッグした場所のブロックを誕生させる
     */
    override fun mouseDragged() {
        beAlive(mouseX / blockSize, mouseY / blockSize)
    }

    /**
     * ゲームを開始する
     */
    fun startLifeGame() { isStart = true }

    /**
     * ゲームを一時停止する
     */
    fun pauseLifeGame() { isStart = false }

    /**
     * ゲームをリセットする
     */
    fun reset() {
        blocks.forEach { it.forEach(Block::reset) }
        isStart = false
    }

    /**
     * 全てのブロックに隣接するブロックを設定する
     */
    fun setRelativeBlocks() {
        blocks.forEach { it.forEach { it.setRelativeBlocks(blocks, lineBlockNum) } }
    }

    /**
     * 全てのブロックの次の世代を計算する
     */
    fun nextPreGeneration() {
        blocks.forEach { it.forEach(Block::nextPreGeneration) }
    }

    /**
     * 全てのブロックの次の世代を設定する
     */
    fun nextGeneration() {
        blocks.forEach { it.forEach(Block::nextGeneration) }
    }

    /**
     * ブロックを誕生・死亡させる
     *
     * @param x ブロックのx座標
     * @param y ブロックのy座標
     */
    fun beAlive(x: Int, y: Int) {
        if (x < 0 || lineBlockNum <= x) return
        if (y < 0 || lineBlockNum <= y) return
        blocks[x][y].isAlive = mouseButton == PConstants.LEFT
    }
}