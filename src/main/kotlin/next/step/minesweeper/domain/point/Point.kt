package next.step.minesweeper.domain.point

data class Point(val x: Int, val y: Int) {
    companion object {
        fun of(x: Int, y: Int) = Point(x, y)
    }
}
