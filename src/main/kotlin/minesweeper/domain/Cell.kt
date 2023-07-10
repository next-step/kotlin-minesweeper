package minesweeper.domain

class Cell(val hasMine: Boolean) {
    companion object {
        val mine = Cell(true)
        val nonMine = Cell(false)
    }
}
