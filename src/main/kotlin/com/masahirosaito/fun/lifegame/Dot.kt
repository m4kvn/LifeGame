package com.masahirosaito.`fun`.lifegame

import processing.core.PApplet

data class Dot(val papplet: PApplet, val x: Int, val y: Int) {
    var isAlive = false
    var preAlive = false

    fun draw() {
        papplet.stroke(0f, if (isAlive) 255f else 0f, 0f)
        papplet.point(x.toFloat(), y.toFloat())
    }
}