package minesweeper.domain

import minesweeper.domain.board.MinesweeperBoard
import minesweeper.domain.flag.BlockFlag
import minesweeper.domain.flag.Flag
import minesweeper.domain.flag.MineFlag

object MinesweeperBoardGenerator {

    fun generate(boardSize: BoardSize, mineCount: PositiveNumber): MinesweeperBoard {
        val initBoard = generateBoard(boardSize = boardSize, mineCount = mineCount)

        return updatedAroundMineBoard(board = initBoard)
    }

    private fun generateBoard(
        boardSize: BoardSize,
        mineCount: PositiveNumber,
    ): Map<Coordinate, Block> {
        val minesweeperArea = boardSize.area
        val mineArea = mineCount.value

        require(value = minesweeperArea >= mineArea) {
            "지뢰의 수는 ${minesweeperArea}보다 클 수 없습니다. 지뢰 수 : $mineArea"
        }

        val mineFlags = List(size = mineArea) { MineFlag() }
        val blockFlags = List(size = minesweeperArea - mineArea) { BlockFlag() }

        return createMinesweeperMap(
            boardSize = boardSize,
            flagDeque = ArrayDeque(elements = (mineFlags + blockFlags).shuffled()),
        )
    }

    private fun createMinesweeperMap(
        boardSize: BoardSize,
        flagDeque: ArrayDeque<Flag>,
    ): Map<Coordinate, Block> = boardSize.rangeWidth()
        .flatMap { createBlock(boardSize = boardSize, x = it, flagDeque = flagDeque) }
        .associateBy { it.coordinate }

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

    private fun updatedAroundMineBoard(board: Map<Coordinate, Block>): MinesweeperBoard {
        val mineAroundCoordinateMap = board.filter { it.value.hasMine }
            .flatMap { it.key.getAroundCoordinates() }
            .groupingBy { it }
            .eachCount()

        mineAroundCoordinateMap.forEach { (coordinate, aroundMineCount) ->
            board[coordinate]?.updateBlock(aroundMineCount = aroundMineCount)
        }

        return MinesweeperBoard(board = board)
    }
}
