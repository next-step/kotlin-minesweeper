package minesweeper.domain

data class Point(val x: Int, val y: Int) : Comparable<Point> {

    init {
        require(x >= 0 && y >= 0) {
            "좌표 값은 음수가 될 수 없습니다."
        }
    }

    override fun compareTo(other: Point) = compareValuesBy(this, other, { it.y }, { it.x })
}
