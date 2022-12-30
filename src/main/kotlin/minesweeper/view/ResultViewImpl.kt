package minesweeper.view

import minesweeper.domain.Block
import minesweeper.domain.ExceptionReason
import minesweeper.domain.MineBlock
import minesweeper.domain.MineSweeperException
import minesweeper.domain.Point
import minesweeper.domain.SafeBlock

class ResultViewImpl : ResultView {
    override fun renderInitialBoard(state: Map<Point, Block>) {
        println()
        println("지뢰찾기 게임 시작")
        renderBoard(state)
    }

    override fun printKnownException(exception: MineSweeperException) {
        println(parseException(exception))
    }

    private fun parseException(exception: MineSweeperException): String {
        return when (exception.reason) {
            ExceptionReason.NEGATIVE_POINT_VALUE -> NEGATIVE_POINT_VALUE_TEXT
            ExceptionReason.MINE_COUNT_OVER_BLOCKS -> MINE_COUNT_OVER_BLOCKS_TEXT
            ExceptionReason.ILLEGAL_NEAR_MINE_RANGE -> ILLEGAL_NEAR_MINE_RANGE_TEXT
            ExceptionReason.ILLEGAL_POINT -> CANT_CREATE_BLOCK
        }
    }

    override fun printUnknownException(exception: Exception) {
        print(exception.message)
    }

    private fun renderBoard(state: Map<Point, Block>) {
        val yList = state.toList().groupBy { it.first.y }
        yList.forEach {
            renderRow(it.value.map { pair -> pair.second })
        }
    }

    private fun renderRow(row: List<Block>) {
        row.forEach(::renderBlock)
        println()
    }

    private fun renderBlock(block: Block) {
        when (block) {
            is MineBlock -> print(DEFAULT_MINE_CHARACTER)
            is SafeBlock -> print(block.nearMineCount)
            else -> print(DEFAULT_BLOCK_CHARACTER)
        }
        print(BLANK_CHARACTER)
    }

    companion object {
        private const val DEFAULT_BLOCK_CHARACTER = "C"
        private const val DEFAULT_MINE_CHARACTER = "*"
        private const val BLANK_CHARACTER = " "
        private const val NEGATIVE_POINT_VALUE_TEXT = "좌표 값은 음수가 될 수 없습니다."
        private const val MINE_COUNT_OVER_BLOCKS_TEXT = "지뢰의 개수는 총 블록의 개수보다 많을 수 없습니다."
        private const val ILLEGAL_NEAR_MINE_RANGE_TEXT = "근처의 지뢰 범위는 0 이상 8 이하입니다."
        private const val CANT_CREATE_BLOCK = "현재 보드에 없는 지점에 지뢰를 설치할 수 없습니다."
    }
}
