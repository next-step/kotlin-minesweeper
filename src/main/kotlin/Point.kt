data class Point(val x: X, val y: Y) {
    companion object {
        fun from(x: Int, y: Int): Point {
            return Point(X(x), Y(y))
        }
    }
}
