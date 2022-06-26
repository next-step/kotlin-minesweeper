package minesweeper.domain

class MineSweeper(
    val boardFields: BoardFields,
) {
    val isEnd get() = isWin || boardFields.hasOpenedMineField()
    val isWin get() = boardFields.isAllOpenedNumberFields()

    fun open(coordinate: Coordinate) {
        check(!isEnd) {
            "게임이 종료된 후에는 필드를 오픈할 수 없습니다."
        }

        val openedField = boardFields.open(coordinate)

        if (openedField is NumberField) {
            openAdjacentNumberFields(coordinate)
        }
    }

    private fun openAdjacentNumberFields(coordinate: Coordinate) {
        val adjacentCoordinates = coordinate.adjacentCoordinates()
        val openNumberFields = boardFields.openNumberFields(adjacentCoordinates)
        openNumberFields.forEach { openAdjacentNumberFields(it.coordinate) }
    }
}
