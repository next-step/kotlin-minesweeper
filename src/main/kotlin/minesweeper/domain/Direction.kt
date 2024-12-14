package minesweeper.domain

enum class Direction(val dy: Int, val dx: Int) {
    NONE(0, 0),
    LEFT_UP(-1, -1),
    UP(-1, 0),
    RIGHT_UP(-1, 1),
    RIGHT(0, 1),
    RIGHT_DOWN(1, 1),
    DOWN(1, 0),
    LEFT_DOWN(1, -1),
    LEFT(0, -1),
    ;

    companion object {
        fun applyTo(point: Point): List<Point> =
            entries
                .filter { direction -> direction.dy + point.row >= 0 && direction.dx + point.col >= 0 }
                .map { direction ->
                    Point(
                        row = direction.dy + point.row,
                        col = direction.dx + point.col,
                    )
                }
    }
}
