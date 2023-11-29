package view

import business.Board
import business.BoardInfo
import business.Point

interface UserInterface {
    fun askBoardInfo(): BoardInfo
    fun askPoint(): Point
    fun displayOpenResult(board: Board)
    fun printWin()
    fun displayGameOver(board: Board)
}
