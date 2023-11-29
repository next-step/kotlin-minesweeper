package view

import business.Board
import business.BoardInfo
import business.Point

interface UserInterface {
    fun askHeight(): Int
    fun askWidth(): Int
    fun askMineCount(): Int
    fun askPoint(): Point
    fun printStartAnnouncement()
    fun printGameOver()
    fun printOpenedResult(isOpen: Boolean, count: Int)
    fun printWin()
    fun printNextLine()
    fun printAll(mines: Boolean, count: Int)
    fun displayGameOver(board: Board)
    fun displayOpenResult(board: Board)
    fun askBoardInfo(): BoardInfo
}
