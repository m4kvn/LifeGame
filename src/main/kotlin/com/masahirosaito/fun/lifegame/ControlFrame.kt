package com.masahirosaito.`fun`.lifegame

import java.awt.BorderLayout
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JPanel

/**
 * LifeGameを操作するフレーム
 *
 * ゲームの開始・一時停止・初期化を行う
 *
 * @property pApplet LifeGame PApplet
 */
class ControlFrame : JFrame() {
    val pApplet = LifeGame()

    init {
        layout = BorderLayout()
        isResizable = true
        title = "Life Game"
        setLocation(100, 100)
        setSize(pApplet.windowSize + 100, pApplet.windowSize + 100)
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE

        add(setButtons(JPanel()), BorderLayout.SOUTH)
        setPApplet()
        pack()

        isVisible = true
    }

    /**
     * ボタンを設定する
     *
     * @param jp ボタンを設定するJPanel
     * @return ボタンを設定したJPanel
     */
    private fun setButtons(jp: JPanel): JPanel = jp.apply {
        add(JButton("開始").apply { addActionListener { pApplet.startLifeGame() } }, BorderLayout.WEST)
        add(JButton("一時停止").apply { addActionListener({ pApplet.pauseLifeGame() }) }, BorderLayout.CENTER)
        add(JButton("初期化").apply { addActionListener { pApplet.reset() } }, BorderLayout.EAST)
    }

    /**
     * PAppletを設定する
     */
    private fun setPApplet() {
        pApplet.frame = this
        add(pApplet, BorderLayout.CENTER)
        pApplet.init()
    }
}