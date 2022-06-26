package minesweeper.domain

data class BoardFields(val boardFields: List<BoardField>) {

    fun aroundFields(coordinate: Coordinate): BoardFields {
        val aroundCoordinates = coordinate.aroundCoordinates()
        val aroundFields = boardFields.filter { aroundCoordinates.contains(it.coordinate) }

        return BoardFields(aroundFields)
    }

    fun adjacentNotOpenedNumberFields(coordinate: Coordinate): BoardFields {
        val adjacentCoordinates = coordinate.adjacentCoordinates()
        val adjacentNotOpenedNumberFields = boardFields.asSequence()
            .filter { adjacentCoordinates.contains(it.coordinate) }
            .filterIsInstance<NumberField>()
            .filterNot { it.isOpen }
            .toList()

        return BoardFields(adjacentNotOpenedNumberFields)
    }

    fun mineCount(): Int {
        return boardFields.count { it is MineField }
    }

    fun open(coordinate: Coordinate): BoardField {
        val boardField = boardField(coordinate)
        boardField.open()

        return boardField
    }

    fun open(): BoardFields {
        val openedFields = boardFields.onEach { it.open() }

        return BoardFields(openedFields)
    }

    fun isAllOpenedNumberFields(): Boolean {
        return boardFields.filterIsInstance<NumberField>()
            .all { it.isOpen }
    }

    fun isOpenedMineField(): Boolean {
        return boardFields.filterIsInstance<MineField>()
            .any { it.isOpen }
    }

    private fun boardField(coordinate: Coordinate): BoardField {
        return boardFields.find { it.coordinate == coordinate }
            ?: throw IllegalArgumentException("해당 좌표에 필드가 존재하지 않습니다. (${coordinate.x},${coordinate.y})")
    }
}
