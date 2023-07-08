package minesweeper.domain

import domain.MineCountNumber
import domain.MinePositionGenerator
import domain.Position
import domain.Positions
import domain.toPositions
import domain.toPositiveNumber

class TestMinePositionGenerator(private vararg val positionValues: Pair<Int, Int>) : MinePositionGenerator {
    override fun generate(): Positions {
        return positionValues.map { (row, column) -> Position.of(row, column) }.toPositions()
    }

    fun getMineCount(height: Int, width:Int): MineCountNumber {
        return MineCountNumber(positionValues.size, height.toPositiveNumber(), width.toPositiveNumber())
    }
}
