package minesweeper.domain

import minesweeper.domain.board.MinesweeperBoard
import minesweeper.domain.flag.EmptyFlag
import minesweeper.domain.flag.Flag
import minesweeper.domain.flag.MineFlag

object MinesweeperBoardGenerator {

    fun generate(boardSize: BoardSize, mineCount: PositiveNumber): MinesweeperBoard {
        val minesweeperArea = boardSize.area()
        val mineArea = mineCount.value

        require(value = minesweeperArea >= mineArea) {
            "지뢰의 수는 ${minesweeperArea}보다 클 수 없습니다. 지뢰 수 : $mineArea"
        }

        val mineFlags = List(size = mineArea) { MineFlag() }
        val emptyFlags = List(size = minesweeperArea - mineArea) { EmptyFlag() }

        return createMinesweeperBoard(
            boardSize = boardSize,
            flagDeque = ArrayDeque(elements = (mineFlags + emptyFlags).shuffled()),
        )
    }

    private fun createMinesweeperBoard(
        boardSize: BoardSize,
        flagDeque: ArrayDeque<Flag>,
    ): MinesweeperBoard = boardSize.rangeWidth()
        .flatMap { createBlock(boardSize = boardSize, x = it, flagDeque = flagDeque) }
        .associateBy { it.coordinate }
        .run(::MinesweeperBoard)

    private fun createBlock(
        x: Int,
        boardSize: BoardSize,
        flagDeque: ArrayDeque<Flag>,
    ): List<Block> = boardSize.rangeHeight()
        .map { y ->
            Block(
                coordinate = Coordinate(
                    x = x,
                    y = y,
                ),
                flag = flagDeque.removeLast(),
            )
        }
}
