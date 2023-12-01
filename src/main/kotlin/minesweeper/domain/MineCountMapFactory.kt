package minesweeper.domain

object MineCountMapFactory : MineMapFactory {
    override fun create(minePositions: Positions, emptyPositions: Positions): MineMap {
        val allPositions = minePositions + emptyPositions
        val cells = allPositions.getValues().associateWith {
            createCell(it, minePositions)
        }
        return MineMap(cells)
    }

    private fun createCell(position: Position, minePositions: Positions): Cell {
        val aroundPositions = position.aroundPositions()
        return if (minePositions.contains(position)) {
            Mine
        } else {
            val mineCount = aroundPositions
                .count { minePositions.contains(it) }
            Empty(mineCount)
        }
    }

}
