package minesweeper.domain

data class BoardFields(val boardFields: List<BoardField>) {

    fun mineCount(coordinate: List<Coordinate>): Int {
        return boardFields.filter { coordinate.contains(it.coordinate) }
            .count { it is MineField }
    }

    fun open(coordinate: Coordinate) {
        val boardField = boardFields.find { it.coordinate == coordinate }
            ?: throw IllegalArgumentException("해당 좌표에 필드가 존재하지 않습니다. (${coordinate.x},${coordinate.y})")

        when (boardField) {
            is MineField -> boardField.open()
            is NumberField -> boardField.open()
                .also { openAdjacentNumberFields(boardField.coordinate) }
        }
    }

    private fun openAdjacentNumberFields(coordinate: Coordinate) {
        val adjacentCoordinates = coordinate.adjacentCoordinates()
        val openNumberFields = boardFields.filter { adjacentCoordinates.contains(it.coordinate) }
            .filterIsInstance<NumberField>()
            .filterNot { it.isOpen }
            .onEach { it.open() }

        openNumberFields.forEach { openAdjacentNumberFields(it.coordinate) }
    }

    fun isAllOpenedNumberFields(): Boolean {
        return boardFields.filterIsInstance<NumberField>()
            .all { it.isOpen }
    }

    fun hasOpenedMineField(): Boolean {
        return boardFields.filterIsInstance<MineField>()
            .any { it.isOpen }
    }
}
