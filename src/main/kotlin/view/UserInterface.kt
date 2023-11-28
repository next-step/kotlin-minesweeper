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
    fun printMinefieldMatrix(maxHeight: Int, maxWidth: Int, mines: Mines)
    fun printGameOver()
    fun printOpenedMinefieldMatrix(maxHeight: Int, maxWidth: Int, mines: Mines, openedCells: OpenedCells)
    fun printWin()
}
