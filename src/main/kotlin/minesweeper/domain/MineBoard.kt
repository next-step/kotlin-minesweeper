package minesweeper.domain

class MineBoard(
    val boardFields: BoardFields,
    isEnd: Boolean = false
) {
    private var _isEnd = isEnd
    val isEnd get() = _isEnd

    fun open(coordinate: Coordinate) {
        when (val openedField = boardFields.open(coordinate)) {
            is NumberField -> openNearFields(openedField)
            is MineField -> _isEnd = true
        }
    }

    private fun openNearFields(field: BoardField) {
        val notOpenedNumberFields = boardFields.nearNotOpenedNumberFields(field.coordinate)
        val openedFields = notOpenedNumberFields.open()
        openedFields.boardFields.forEach { openNearFields(it) }
    }
}
