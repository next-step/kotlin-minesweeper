package minesweeper.domain

interface CellState {
    fun open(): CellState
}

class Closed(private val cell: Cell) : CellState {
    override fun open(): CellState {
        return if (cell.isMine()) {
            this
        } else {
            Open()
        }
    }
}

class Open : CellState {
    override fun open(): CellState {
        return this
    }
}