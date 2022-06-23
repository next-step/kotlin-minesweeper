package minesweeper.domain

data class BoardFields(val boardFields: List<BoardField>) {

    fun nearFields(coordinate: Coordinate): BoardFields {
        val nearCoordinate = coordinate.nearCoordinate()
        val nearFields = boardFields.filter { nearCoordinate.contains(it.coordinate) }

        return BoardFields(nearFields)
    }

    fun aroundNotOpenedNumberFields(coordinate: Coordinate): BoardFields {
        val nearCoordinate = coordinate.aroundCoordinate()
        val fields = boardFields.asSequence()
            .filter { nearCoordinate.contains(it.coordinate) }
            .filterIsInstance<NumberField>()
            .filterNot { it.isOpen }
            .toList()

        return BoardFields(fields)
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
