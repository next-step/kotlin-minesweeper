package minesweeper.domain

class Mines(val mines: List<Mine>) {

    fun count(): Int {
        return mines.size
    }
}
