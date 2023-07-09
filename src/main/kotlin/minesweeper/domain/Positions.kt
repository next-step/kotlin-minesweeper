package minesweeper.domain

class Positions(
    val positions: Array<Array<Position>>,
) {
    val rows: Int = positions.size
    val cols: Int = positions[0].size

    fun position(rows: Int, cols: Int): Position {
        return positions[rows][cols]
    }

    fun updatePositionValue(rows: Int, cols: Int, value: Int) {
        positions[rows][cols] = Position(value)
    }

    fun open(row: Int, col: Int) {
        val position = positions[row][col]
        position.isOpened = true
        position.isVisited = true

        val findPositions = FindPosition.positions(row, col, rows, cols)

        val isMineNotExists = findPositions.none { isMinePosition(it.first, it.second) }

        if (isMineNotExists) {
            openPositions(findPositions)
            addFindOpenPositions(findPositions).forEach { (first, second) -> open(first, second) }
        }
    }

    fun notOpenPositionCount(): Int {
        return positions
            .asSequence()
            .flatMap { it.asSequence() }
            .filter { !it.isOpened }
            .count()
    }

    fun isMinePosition(row: Int, col: Int): Boolean {
        return position(row, col).value == -1
    }

    private fun openPositions(findPositions: List<Pair<Int, Int>>) {
        findPositions
            .forEach { (first, second) -> positions[first][second].isOpened = true }
    }

    private fun addFindOpenPositions(findPositions: List<Pair<Int, Int>>): List<Pair<Int, Int>> {
        return findPositions
            .filter { isNotVisited(it.first, it.second) }
    }

    private fun isNotVisited(row: Int, col: Int): Boolean {
        return !position(row, col).isVisited
    }
}
