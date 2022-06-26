package minesweeper.domain

data class BoardFields(val boardFields: List<BoardField>) {

    fun mineCount(coordinate: List<Coordinate>): Int {
        return boardFields.filter { coordinate.contains(it.coordinate) }
            .count { it is MineField }
    }

    fun open(coordinate: Coordinate): BoardField {
        val boardField = boardFields.find { it.coordinate == coordinate }
            ?: throw IllegalArgumentException("해당 좌표에 필드가 존재하지 않습니다. (${coordinate.x},${coordinate.y})")
        boardField.open()

        return boardField
    }

    fun openNumberFields(coordinates: List<Coordinate>): List<BoardField> {
        return boardFields.filter { coordinates.contains(it.coordinate) }
            .filterIsInstance<NumberField>()
            .onEach { it.open() }
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
