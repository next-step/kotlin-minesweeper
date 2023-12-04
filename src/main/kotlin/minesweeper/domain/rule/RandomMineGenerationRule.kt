package minesweeper.domain.rule

import minesweeper.domain.*

class RandomMineGenerationRule : MineGenerationRule {
    override fun generate(metadata: BoardMetadata): Map<Coordinate, Cell> {
        val coordinates = (0 until metadata.width).flatMap { row ->
            (0 until metadata.height).map { col ->
                Coordinate(row, col)
            }
        }
        val mineCoordinates = coordinates.shuffled().take(metadata.numOfMine)
        return coordinates.associateWith {
            if (mineCoordinates.contains(it)) {
                return@associateWith MineCell
            }

            EmptyCell
        }
    }
}
