package domain

data class Board(val rows: List<Row>) {

    init {
        require(rows.isNotEmpty()) { "적어도 하나의 row 가 필요합니다" }
        require(rows.map { it.size }.distinct().size == 1) { "모든 row 의 크기는 같아야 합니다" }
    }

    val mineCount = rows.flatMap { it.cells }.count { it is Mine }

    val cellCount = rows.first().size * rows.size
}
