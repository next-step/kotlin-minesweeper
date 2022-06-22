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
}
