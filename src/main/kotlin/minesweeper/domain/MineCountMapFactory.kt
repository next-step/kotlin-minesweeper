package minesweeper.domain

class MineCountMapFactory(
    private val positionGenerator: PositionGenerator
) : MineMapFactory {
    override fun create(): MineMap {
        val minePositions = positionGenerator.generateMinePositions()
        val emptyPositions = positionGenerator.generateEmptyPositions(minePositions)
        val cells = (minePositions + emptyPositions)
            .getValues()
            .associateWith { createCell(it, minePositions) }
        return MineMap(cells)
    }

    private fun createCell(position: Position, minePositions: Positions): Cell {
        val aroundPositions = position.aroundPositions()
        return if (minePositions.contains(position)) {
            Mine()
        } else {
            val mineCount = aroundPositions.count { minePositions.contains(it) }
            Empty(mineCount)
        }
    }
}
