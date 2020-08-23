package minesweeper.model

import minesweeper.model.block.Block

class Board(val row: Int, val col: Int) {
    var board: List<List<Block>> = List(row) { List(col) { Block() } }

    constructor(board: List<List<Block>>) : this(board.size, board[0].size) {
        this.board = board
    }

    fun setMinePosition(mineCount: Int): Board {
        val minePositions = MineBoardMaker().setRandomMinePosition(mineCount, getTotal())

        for (minePosition in minePositions) {
            val block = getBlock(minePosition)

            block.setMine()
        }
        return Board(this.board)
    }

    fun setNearbyMineCount(): Board {
        val counter = NearByMineCounter()

        for (position in START_POSITION until getTotal()) {

            val block = getBlock(position)

            if (block.type == Type.MINE) continue

            block.setCount(counter.getMineNumber(position, this))
        }
        return Board(this.board)
    }

    fun getTotal(): Int {
        return row * col
    }

    private fun getBlock(position: Int): Block {
        return board[getRow(position)][getCol(position)]
    }

    fun getRow(position: Int): Int {
        return position / col
    }

    fun getCol(position: Int): Int {
        return position % col
    }
}
