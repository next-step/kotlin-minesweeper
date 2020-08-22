package minesweeper.model

import minesweeper.model.block.Block

class Board(val row: Int, val col: Int) {
    var board: List<List<Block>> = List(row) { List(col) { Block() } }

    constructor(board: Board) : this(board.getRow(board.getTotal()), board.getCol(board.getTotal())) {
        this.board = board.board
    }

    fun getTotal(): Int {
        return row * col
    }

    fun getBlock(position: Int): Block {
        return board[getRow(position)][getCol(position)]
    }

    fun getRow(position: Int): Int {
        return position / col
    }

    fun getCol(position: Int): Int {
        return position % col
    }
}
