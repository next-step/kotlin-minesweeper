package view

import business.Mines

interface UserInterface {
    fun askHeight(): Int
    fun askWidth(): Int
    fun askMineCount(): Int
    fun printStartAnnouncement()
    fun printMinefieldMatrix(height: Int, width: Int, mines: Mines)
}
