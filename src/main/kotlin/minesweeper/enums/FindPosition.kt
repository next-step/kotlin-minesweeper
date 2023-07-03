package minesweeper.enums

enum class FindPosition(val row: Int, val col: Int) {
    LEFT_UP(-1, -1),
    LEFT(-1, 0),
    LEFT_DOWN(-1, 1),
    UP(0, 1),
    DOWN(0, -1),
    RIGHT_UP(1, -1),
    RIGHT(1, 0),
    RIGHT_DOWN(1, 1);

    companion object {
        fun positions(currentRow: Int, currentCol: Int): List<Pair<Int, Int>> {
            val pairs = mutableListOf<Pair<Int, Int>>()
            values().forEach {
                pairs += Pair(it.row.plus(currentRow), it.col.plus(currentCol))
            }
            return pairs
        }
    }
}
