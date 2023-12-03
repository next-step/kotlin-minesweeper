package minesweeper

import minesweeper.domain.board.size.MineSweeperBoardSize

class MineSweeper(
    private val board: MineSweeperBoardSize,
    private val mineCount: Int
) {
    init {
        require(mineCount > 0) { INVALID_VALUE }
    }

    fun initialize(): List<List<String>> {
        val initialList = (1..(board.getBoardFullSize() - mineCount)).map {
            "C"
        }
        val mineList = (1..mineCount).map {
            "*"
        }
        val mineSweeperList = (initialList + mineList).shuffled()
        return getResultList(mineSweeperList)
    }

    private fun getResultList(initialList: List<String>): List<List<String>> {
        return (0 until board.width).map { height ->
            val line = height * board.width
            initialList.slice(line until line + board.width)
        }
    }

    companion object {
        private const val INVALID_VALUE = "잘못된 값을 입력하셨습니다."
    }
}
