package minesweeper.domain

import minesweeper.domain.board.size.MineSweeperBoardSize

class MineSweeperBoard(
    private val mineSweeperWidths: List<MineSweeperWidth>
) : List<MineSweeperWidth> by mineSweeperWidths {
    companion object {
        fun newInstance(boardSize: MineSweeperBoardSize, mineSweeperList: MineSweeperWidth): MineSweeperBoard {
            return List(boardSize.height) {
                val line = it * boardSize.width
                mineSweeperList.slice(line until line + boardSize.width).toMineSweeperWidth()
            }.toMineSweeperBoard()
        }
    }
}

fun List<MineSweeperWidth>.toMineSweeperBoard() = MineSweeperBoard(this)
