package minesweeper_refactor.domain.board.builder

import kotlin.properties.Delegates
import minesweeper_refactor.domain.board.BoardSize
import minesweeper_refactor.domain.board.MinesweeperBoard

fun minesweeperBoard(block: BoardBuilder.() -> Unit): MinesweeperBoard = BoardBuilder().apply(block = block).build()

class BoardBuilder : DslBuilder<MinesweeperBoard>() {

    private lateinit var boardSize: BoardSize
    private var mineCount by Delegates.notNull<Int>()

    fun mineCount(value: Int) {
        mineCount = value
    }

    fun boardSize(block: BoardSizeBuilder.() -> Unit) {
        boardSize = BoardSizeBuilder().apply(block = block).build()
    }

    override fun build(): MinesweeperBoard {
        val area = boardSize.area
        val mineArea = mineCount

        require(value = area >= mineArea) { "지뢰의 수는 ${area}보다 클 수 없습니다. 지뢰 수 : $mineArea" }

        val blockGenerator = boardSize.toBlockGenerator()

        val mineBlocks = blockGenerator toMineBlocks mineArea
        val mineAroundCoordinateMap = mineBlocks.toAroundCoordinateMap()
        val numberBlocks = blockGenerator.toRemainNumberBlocks {
            mineAroundCoordinateMap.getOrDefault(key = it, DEFAULT_MINE_COUNT)
        }

        return MinesweeperBoard(numberBlocks = numberBlocks, mineBlocks = mineBlocks)
    }

    companion object {
        private const val DEFAULT_MINE_COUNT: Int = 0
    }
}
