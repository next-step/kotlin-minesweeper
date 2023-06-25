package minesweeper.domain

import minesweeper.domain.board.MinesweeperBoard
import minesweeper.domain.flag.BlockFlag
import minesweeper.domain.flag.MineFlag

object MinesweeperBoardGenerator {

    private const val DEFAULT_MINE_COUNT: Int = 0

    fun generate(boardSize: BoardSize, mineCount: PositiveNumber): MinesweeperBoard {
        val minesweeperArea = boardSize.area
        val mineArea = mineCount.value

        require(value = minesweeperArea >= mineArea) {
            "지뢰의 수는 ${minesweeperArea}보다 클 수 없습니다. 지뢰 수 : $mineArea"
        }

        return createInitBlocks(
            mineArea = mineArea,
            boardCoordinates = createShuffledCoordinates(boardSize = boardSize),
            minesweeperArea = minesweeperArea,
        ).associateBy { it.coordinate }
            .run(::MinesweeperBoard)
    }

    private fun createShuffledCoordinates(boardSize: BoardSize): ArrayDeque<Coordinate> = boardSize.rangeWidth()
        .flatMap { createCoordinates(boardSize = boardSize, x = it) }
        .shuffled()
        .run(::ArrayDeque)

    private fun createCoordinates(x: Int, boardSize: BoardSize): List<Coordinate> = boardSize.rangeHeight()
        .map { y -> Coordinate(x = x, y = y) }

    private fun createInitBlocks(
        mineArea: Int,
        boardCoordinates: ArrayDeque<Coordinate>,
        minesweeperArea: Int,
    ): List<Block> {
        val mineBlocks = List(size = mineArea) {
            Block(coordinate = boardCoordinates.removeLast(), flag = MineFlag())
        }

        val mineAroundCoordinateMap = mineBlocks.flatMap { it.coordinate.getAroundCoordinates() }
            .groupingBy { it }
            .eachCount()

        val numberBlocks = List(size = minesweeperArea - mineArea) {
            val coordinate = boardCoordinates.removeLast()
            createNumberBlock(coordinate = coordinate, mineAroundCoordinateMap = mineAroundCoordinateMap)
        }

        return mineBlocks + numberBlocks
    }

    private fun createNumberBlock(
        coordinate: Coordinate,
        mineAroundCoordinateMap: Map<Coordinate, Int>,
    ): Block = Block(
        coordinate = coordinate,
        flag = BlockFlag(
            aroundMineCount = mineAroundCoordinateMap.getOrDefault(
                key = coordinate,
                defaultValue = DEFAULT_MINE_COUNT,
            ),
        ),
    )
}
