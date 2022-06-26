package minesweeper.domain

class MineSweeper(
    val boardFields: BoardFields,
) {
    val isEnd get() = isWin || boardFields.isOpenedMineField()
    val isWin get() = boardFields.isAllOpenedNumberFields()

    fun open(coordinate: Coordinate) {
        check(!isEnd) {
            "게임이 종료된 후에는 필드를 오픈할 수 없습니다."
        }

        if (boardFields.open(coordinate) is NumberField) {
            openAdjacentFields(boardFields.open(coordinate))
        }
    }

    private fun openAdjacentFields(field: BoardField) {
        val adjacentNotOpenedNumberFields = boardFields.adjacentNotOpenedNumberFields(field.coordinate)
        val openedFields = adjacentNotOpenedNumberFields.open()
        openedFields.boardFields.forEach { openAdjacentFields(it) }
    }
}
