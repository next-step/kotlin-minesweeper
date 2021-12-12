package minesweeper.fixture

import minesweeper.domain.Board
import minesweeper.domain.area.Area
import minesweeper.domain.area.Height
import minesweeper.domain.area.Width
import minesweeper.domain.block.Block
import minesweeper.domain.block.EmptyBlock
import minesweeper.domain.block.MineBlock
import minesweeper.domain.block.Position

object BoardFixture {

    fun createBoard(widthInt: Int, heightInt: Int, minesCountInt: Int): Board {
        val area = Area(Width(widthInt), Height(heightInt))
        val positions = createPositions(area.width, area.height)
        val minesPositions = createBoardGenerateStrategy(positions, minesCountInt)
        return Board(positions.map { minesOrCell(it, minesPositions) })
    }

    fun createPositions(width: Int, height: Int): List<Position> =
        (1..width).flatMap { x ->
            (1..height).map { y ->
                Position(x, y)
            }
        }

    fun createBoardGenerateStrategy(positions: List<Position>, mineCount: Int): List<Position> =
        positions.subList(0, mineCount)

    fun minesOrCell(positions: Position, minesPositions: List<Position>): Block {
        if (minesPositions.contains(positions)) {
            return MineBlock(positions)
        }
        return EmptyBlock(positions)
    }
}
