package com.masahirosaito.`fun`.lifegame

import processing.core.PApplet

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

    fun draw() {
        pApplet.fill(0f, if (isAlive) 255f else 0f, 0f)
        pApplet.rect(drawX, drawY, size, size)
    }

    fun getRelativesAliveNum(): Int = relatives.filter { it.isAlive }.size

    fun nextGeneration() { isAlive = preAlive }

    fun nextPreGeneration() {
        getRelativesAliveNum().apply {
            preAlive = if (isAlive) this == 2 || this == 3 else this == 3
        }
    }

    fun reset() {
        isAlive = false
        preAlive = false
    }
}