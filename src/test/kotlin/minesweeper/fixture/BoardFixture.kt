package minesweeper.fixture

import minesweeper.domain.area.Area
import minesweeper.domain.area.Height
import minesweeper.domain.area.Width
import minesweeper.domain.block.Block
import minesweeper.domain.block.Blocks
import minesweeper.domain.block.Position
import minesweeper.domain.block.strategy.MineBlockGenerateStrategy
import minesweeper.domain.board.Board

object BoardFixture {

    val TEST_MINE_BLOCK_GENERATE_STRATEGY =
        MineBlockGenerateStrategy { positions, mineCount -> positions.subList(0, mineCount) }

    fun createBoard(
        width: Int,
        height: Int,
        minesCountInt: Int,
        mineBlockGenerateStrategy: MineBlockGenerateStrategy
    ): Board {
        val area = Area(Width(width), Height(height))
        val positions = createPositions(area.width, area.height)
        val minePositions = mineBlockGenerateStrategy.generate(positions, minesCountInt)
        return Board(createBlocks(positions, minePositions))
    }

    fun createBlocks(
        width: Int,
        height: Int,
        minesCountInt: Int,
        mineBlockGenerateStrategy: MineBlockGenerateStrategy
    ): Blocks {
        val area = Area(Width(width), Height(height))
        val positions = createPositions(area.width, area.height)
        val minePositions = mineBlockGenerateStrategy.generate(positions, minesCountInt)
        return createBlocks(positions, minePositions)
    }

    fun createPositions(width: Int, height: Int): List<Position> =
        (1..width).flatMap { x ->
            (1..height).map { y ->
                Position(x, y)
            }
        }

    fun createBlocks(positions: List<Position>, minesPositions: List<Position>): Blocks =
        Blocks(positions.associateWith { Block.create(it in minesPositions) })
}
