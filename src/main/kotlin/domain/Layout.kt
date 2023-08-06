package domain

data class Layout(
    private val boardSize: BoardSize
) {
    val rows: List<Row> = List(boardSize.height) {
        Row(List(boardSize.width) { Cell(CellStatus.EMPTY) }.toMutableList()).copy()
    }

    init {
        check(rows.size == boardSize.height) {
            "Layout 의 row 개수는 board 의 높이와 같아야합니다. [row size: ${rows.size} ]"
        }
    }

    operator fun get(y: Int): Row {
        return rows[y].copy()
    }
}
