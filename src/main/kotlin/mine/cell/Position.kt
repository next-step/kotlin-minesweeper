package mine.cell

/**
 * 셀의 위치 정보 관리
 * */
data class Position(val x: Int, val y: Int) {
    fun aroundPosition(): List<Position> {
        val xList = listOf(x - 1, x, x + 1)
        val yList = listOf(y - 1, y, y + 1)
        return xList
            .flatMap { x ->
                yList.map { Position(x, it).ofNullable() }
            }
            .filterNotNull()
            .filterNot { it.x == x && it.y == y }
    }

    companion object {
        private const val MIN_POSITION = 0
        private const val DELIMITER = ","

        fun Position.ofNullable(): Position? = if (x >= MIN_POSITION && y >= MIN_POSITION) Position(x, y) else null
        fun String?.ofPosition(): Position {
            require(this != null)
            val ints = this.split(DELIMITER).mapNotNull { it.toIntOrNull() }
            return Position(ints[0], ints[1])
        }
    }
}
