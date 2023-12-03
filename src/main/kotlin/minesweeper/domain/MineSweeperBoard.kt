package minesweeper.domain

import minesweeper.domain.board.size.MineSweeperBoardSize

class MineSweeperBoard(
    private val mineSweeperWidths: List<MineSweeperWidth>
) : List<MineSweeperWidth> by mineSweeperWidths {
    companion object {
        fun newInstance(board: MineSweeperBoardSize, mineSweeperList: List<String>): MineSweeperBoard {
            return (0 until board.height).map { height ->
                val line = height * board.width
                mineSweeperList.slice(line until line + board.width).toMineSweeperWidth()
            }.toMineSweeperBoard()
        }
    }
}

fun List<MineSweeperWidth>.toMineSweeperBoard() = MineSweeperBoard(this)
