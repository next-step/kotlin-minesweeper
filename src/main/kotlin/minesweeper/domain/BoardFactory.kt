package minesweeper.domain

class BoardFactory(private val randomGenerator: RandomGenerator) {

    fun createBy(height: Int, width: Int, mineCount: Int): Board {
        val positions = createPositions(height, width)
        val minePositions = getRandomPositions(height, width, mineCount).map { positions[it] }
        val positionWithCells = positions.associateWith {
            createCell(it, minePositions)
        }
        return Board(positionWithCells)
    }

    private fun createPositions(height: Int, width: Int): List<Position> {
        return (Row.START_VALUE..height).flatMap { row ->
            (Column.START_VALUE..width).map { column ->
                Position.from(row = row, column = column)
            }
        }
    }

    private fun getRandomPositions(height: Int, width: Int, mineCount: Int): List<Int> {
        return randomGenerator.generate(until = height * width, count = mineCount)
    }

    private fun createCell(position: Position, minePositions: List<Position>): Cell {
        return if (position in minePositions) {
            MineCell
        } else {
            BlockCell
        }
    }
}
