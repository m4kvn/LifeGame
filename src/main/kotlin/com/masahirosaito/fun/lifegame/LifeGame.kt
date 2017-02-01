package com.masahirosaito.`fun`.lifegame

import processing.core.PApplet
import processing.core.PConstants

class LifeGame : PApplet() {
    val windowSize = 500
    val blockSize = 10
    val n = windowSize / blockSize
    var start = false

    val blocks = Array(n, { x ->
        Array(n, { y ->
            Block(this, x, y, blockSize.toFloat())
        })
    })

    override fun setup() {
        frameRate(10f)
        size(windowSize, windowSize)
        background(0)
        stroke(50)

        blocks.forEach { it.forEach { block -> setRelativeBlocks(block) } }
    }

    override fun draw() {
        background(0)

        if (start) {
            nextPreGeneration()
            blocks.forEach { it.forEach(Block::nextGeneration) }
        }

        blocks.forEach { it.forEach(Block::draw) }
    }

    override fun keyPressed() {
        when (key) {
            's' -> startLifeGame()
            'p' -> pauseLifeGame()
            'n' -> nextPreGeneration()
            'r' -> reset()
        }
    }

    override fun mouseClicked() {
        val x = mouseX / blockSize
        val y = mouseY / blockSize

        when (mouseButton) {
            PConstants.LEFT -> blocks[x][y].isAlive = true
            PConstants.RIGHT -> blocks[x][y].isAlive = false
        }

        println(blocks[x][y].getRelativesAliveNum())
    }

    override fun mouseDragged() {
        val x = mouseX / blockSize
        val y = mouseY / blockSize

        when (mouseButton) {
            PConstants.LEFT -> blocks[x][y].isAlive = true
            PConstants.RIGHT -> blocks[x][y].isAlive = false
        }
    }

    fun startLifeGame() { start = true }

    fun pauseLifeGame() { start = false }

    fun setRelativeBlocks(block: Block) {
        val x = block.x
        val y = block.y

        for (i in x-1..x+1) for (j in y-1..y+1) {
            when {
                i == x && j == y -> {}
                i < 0 || n <= i -> {}
                j < 0 || n <= j -> {}
                else -> block.relatives.add(blocks[i][j])
            }
        }
    }

    fun nextPreGeneration() {
        blocks.forEach { it.forEach(Block::nextPreGeneration) }
    }

    fun reset() {
        blocks.forEach { it.forEach(Block::reset) }
    }
}