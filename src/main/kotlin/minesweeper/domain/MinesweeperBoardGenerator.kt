package minesweeper.domain

import minesweeper.domain.board.MinesweeperBoard
import minesweeper.domain.flag.EmptyFlag
import minesweeper.domain.flag.Flag
import minesweeper.domain.flag.MineFlag

object MinesweeperBoardGenerator {

    fun generate(width: BoardSize, height: BoardSize, mineCount: PositiveNumber): MinesweeperBoard {
        val minesweeperArea = (width * height).value
        val mineArea = mineCount.value

        require(value = minesweeperArea >= mineArea) {
            "지뢰의 수는 ${minesweeperArea}보다 클 수 없습니다. 지뢰 수 : $mineArea"
        }

        val mineFlags = List(size = mineArea) { MineFlag() }
        val emptyFlags = List(size = minesweeperArea - mineArea) { EmptyFlag() }

        return createMinesweeperBoard(
            width = width,
            height = height,
            flagDeque = ArrayDeque(elements = (mineFlags + emptyFlags).shuffled()),
        )
    }

    private fun createMinesweeperBoard(
        width: BoardSize,
        height: BoardSize,
        flagDeque: ArrayDeque<Flag>,
    ): MinesweeperBoard = width.rangeZeroToSize()
        .flatMap { createBlock(height = height, x = it, flagDeque = flagDeque) }
        .associateBy { it.coordinate }
        .run(::MinesweeperBoard)

    private fun createBlock(
        height: BoardSize,
        x: Int,
        flagDeque: ArrayDeque<Flag>,
    ): List<Block> = height.rangeZeroToSize()
        .map { y ->
            Block(
                coordinate = Coordinate(
                    x = Point(value = x),
                    y = Point(value = y),
                ),
                flag = flagDeque.removeLast(),
            )
        }
}
