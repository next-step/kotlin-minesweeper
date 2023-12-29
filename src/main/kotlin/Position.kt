data class Position(val y: Int, val x: Int) {
    companion object {
        fun createInRange(width: Int, height: Int): List<Position> {
            return (0 until width).flatMap { x ->
                (0 until height).map { y ->
                    Position(y, x)
                }
            }
        }
    }
}
