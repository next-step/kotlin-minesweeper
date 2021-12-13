package mine.cell

/**
 * 셀의 위치 정보 관리
 * */
data class Position(val x: Int, val y: Int) {
    init {
        require(x >= MIN_POSITION && y >= MIN_POSITION)
    }

    fun aroundPosition(): List<Position> {
        val xList = listOf(x - 1, x, x + 1)
        val yList = listOf(y - 1, y, y + 1)
        return xList
            .flatMap { x ->
                yList.map { ofNullable(x, it) }
            }
            .filterNotNull()
            .filterNot { it.x == x && it.y == y }
    }

    fun aroundAllPosition(): List<Position> = aroundPosition() + this

    companion object {
        private const val MIN_POSITION = 0
        private const val DELIMITER = ","

        fun ofNullable(x: Int, y: Int): Position? = if (x >= MIN_POSITION && y >= MIN_POSITION) Position(x, y) else null
        fun String?.ofPosition(): Position {
            require(this != null)
            val ints = this.split(DELIMITER).mapNotNull { it.toIntOrNull() }
            require(ints.size == 2 && ints.isNotEmpty())
            return Position(ints[0], ints[1])
        }
    }
}
