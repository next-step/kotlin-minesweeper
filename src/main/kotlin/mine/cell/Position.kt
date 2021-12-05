package mine.cell

/**
 * 셀의 위치 정보 관리
 * */
data class Position(val x: Int, val y: Int) {
    init {
        require(x >= MIN_POSITION && y >= MIN_POSITION)
    }

    fun aroundPosition(): List<Position> {
        val xList = listOf(x - 1, x, x + 1).filter { it >= 0 }
        val yList = listOf(y - 1, y, y + 1).filter { it >= 0 }
        return xList
            .flatMap { x ->
                yList.map { Position(x, it) }
            }
            .filterNot { it.x == x && it.y == y }
    }

    companion object {
        const val MIN_POSITION = 0
    }
}
