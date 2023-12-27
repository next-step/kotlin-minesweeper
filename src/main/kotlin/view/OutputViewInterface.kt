package view

import map.Board

interface OutputViewInterface {
    fun drawBoard(board: Board)

    fun printGameClear()

    fun printGameOver()
}
