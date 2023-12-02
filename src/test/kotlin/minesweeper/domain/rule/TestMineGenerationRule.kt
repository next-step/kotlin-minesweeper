package minesweeper.domain.rule

import minesweeper.domain.*

class TestMineGenerationRule(private val mineCoordinates: List<Coordinate>) : MineGenerationRule {

    override fun generate(metadata: BoardMetadata): Map<Coordinate, Cell> {
        val coordinates = (0 until metadata.width).flatMap { row ->
            (0 until metadata.height).map { col ->
                Coordinate(row, col)
            }
        }
        return coordinates.associateWith {
            if (mineCoordinates.contains(it)) {
                return@associateWith MineCell()
            }

            EmptyCell()
        }
    }
}
