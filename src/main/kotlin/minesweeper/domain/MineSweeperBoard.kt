package minesweeper.domain

import minesweeper.domain.board.size.MineSweeperBoardSize
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
        var count = 0
        if (MineSweeperShape.isMine(mineSweeperBoard[widthIndex][heightIndex])) return@List mineSweeperBoard[widthIndex][heightIndex]
        if (valueIsMine(widthIndex = widthIndex - 1, heightIndex = heightIndex - 1)) count += 1
        if (valueIsMine(widthIndex = widthIndex, heightIndex = heightIndex - 1)) count += 1
        if (valueIsMine(widthIndex = widthIndex + 1, heightIndex = heightIndex - 1)) count += 1
        if (valueIsMine(widthIndex = widthIndex - 1, heightIndex = heightIndex)) count += 1
        if (valueIsMine(widthIndex = widthIndex + 1, heightIndex = heightIndex)) count += 1
        if (valueIsMine(widthIndex = widthIndex - 1, heightIndex = heightIndex + 1)) count += 1
        if (valueIsMine(widthIndex = widthIndex, heightIndex = heightIndex + 1)) count += 1
        if (valueIsMine(widthIndex = widthIndex + 1, heightIndex = heightIndex + 1)) count += 1
        count.toString()
    }.toMineSweeperWidth()

    private fun valueIsMine(widthIndex: Int, heightIndex: Int): Boolean {
        return runCatching {
            MineSweeperShape.isMine(mineSweeperBoard[widthIndex][heightIndex])
        }.getOrNull() ?: false
    }

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
