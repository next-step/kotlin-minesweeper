package domain

class Board(rows: List<Row>) {
    private val rows = rows.toList()

    val height: Int
        get() = rows.size

    val width: Int
        get() = rows.first().width

    init {
        require(rows.isNotEmpty()) { "빈 rows 로는 Board 를 만들 수 없습니다!" }
        require(rows.all { it.width == rows.first().width }) { "모든 row 의 길이가 같아야 합니다!" }
    }
}
