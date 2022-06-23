package minesweeper.domain

data class BoardFields(val boardFields: List<BoardField>) {

    fun nearFields(coordinate: Coordinate): BoardFields {
        val nearCoordinate = coordinate.nearCoordinate()
        val nearFields = boardFields.filter { nearCoordinate.contains(it.coordinate) }

        return BoardFields(nearFields)
    }

    fun mineCount(): Int {
        return boardFields.count { it is MineField }
    }

    fun open(coordinate: Coordinate) {
        val boardField = boardFields.find { it.coordinate == coordinate }
            ?: throw IllegalArgumentException("해당 좌표에 필드가 존재하지 않습니다. (${coordinate.x},${coordinate.y})")
        boardField.open()
    }
}
