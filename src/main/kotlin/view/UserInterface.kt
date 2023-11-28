package view

import business.Mines
import business.OpenedCells
import business.Point

interface UserInterface {
    fun askHeight(): Int
    fun askWidth(): Int
    fun askMineCount(): Int
    fun askPoint(): Point
    fun printStartAnnouncement()
    fun printMinefieldMatrix(height: Int, width: Int, mines: Mines)
    fun printGameOver()
    fun printOpenedMinefieldMatrix(height: Int, width: Int, mines: Mines, openedCells: OpenedCells)
    fun printWin()
}
