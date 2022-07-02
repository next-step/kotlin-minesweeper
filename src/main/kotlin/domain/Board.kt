package domain

class Board private constructor(val rows: List<Row>) {

    val mineCount = rows.flatMap { it.cells }.count { it is Mine }

    val cellCount = rows.first().size * rows.size

    fun mineCount(cell: Cell): Int =
        rows.flatMap { it.cells }.count { it is Mine && it.isAdjacentTo(cell) }

    fun open(cell: Cell): GameStatus {
        when (cell) {
            is Mine -> return GameStatus.LOST
            is Empty -> cell.open()
        }

        return if (isClear()) GameStatus.WIN
        else GameStatus.CONTINUE
    }

    private fun isClear(): Boolean =
        rows.flatMap { it.cells }.filterIsInstance<Empty>().all { it.opened }

    companion object {

        fun of(rows: List<Row>): Board {
            require(rows.isNotEmpty()) { "적어도 하나의 row 가 필요합니다" }
            require(rows.map { it.size }.distinct().size == 1) { "모든 row 의 크기는 같아야 합니다" }

            return Board(rows)
        }
    }
}
