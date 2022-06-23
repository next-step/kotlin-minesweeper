package minesweeper.domain

class MineBoard(
    val boardFields: BoardFields,
) {
    val isEnd get() = boardFields.isAllOpenedNumberFields() || boardFields.isOpenedMineField()
    val isWin get() = boardFields.isAllOpenedNumberFields()

    fun open(coordinate: Coordinate) {
        require(!isEnd) {
            "게임이 종료된 후에는 필드를 오픈할 수 없습니다."
        }

        if (boardFields.open(coordinate) is NumberField) {
            openNearFields(boardFields.open(coordinate))
        }
    }

    private fun openNearFields(field: BoardField) {
        val notOpenedNumberFields = boardFields.aroundNotOpenedNumberFields(field.coordinate)
        val openedFields = notOpenedNumberFields.open()
        openedFields.boardFields.forEach { openNearFields(it) }
    }
}
