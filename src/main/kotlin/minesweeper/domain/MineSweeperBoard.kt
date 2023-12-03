package minesweeper.domain

import minesweeper.domain.board.size.MineSweeperBoardSize
import minesweeper.domain.mine.MineCheckStrategy
import minesweeper.domain.mine.MineSweeperShape

class MineSweeperBoard(
    private val mineSweeperBoard: List<MineSweeperWidth>
) : List<MineSweeperWidth> by mineSweeperBoard {
    fun resultBoard(): MineSweeperBoard {
        return mineSweeperBoard.mapIndexed { heightIndex, height ->
            resultMineSweeperWidth(height, heightIndex)
        }.toMineSweeperBoard()
    }

    private fun resultMineSweeperWidth(
        height: MineSweeperWidth,
        heightIndex: Int
    ): MineSweeperWidth = List(height.size) { widthIndex ->
        if (MineSweeperShape.isMine(mineSweeperBoard[widthIndex][heightIndex])) return@List mineSweeperBoard[widthIndex][heightIndex]
        val resultCount = MineCheckStrategy.mineMatchCount(
            mineSweeperBoard = this,
            widthIndex = widthIndex,
            heightIndex = heightIndex
        )
        resultCount.toString()
    }.toMineSweeperWidth()

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
