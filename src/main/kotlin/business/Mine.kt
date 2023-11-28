package business

data class Mine(val point: Point) {
    companion object {
        fun of(x: Int, y: Int): Mine = Mine(Point(x, y))
    }
}
