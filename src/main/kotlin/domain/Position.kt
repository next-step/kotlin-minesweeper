package domain

data class Position(
    val x: Int,
    val y: Int
) {
    companion object {
        private const val POSITION_START = 1

        fun createAll(width: Int, height: Int): List<Position> {
            return (POSITION_START..height).flatMap { y ->
                (POSITION_START..width).map { Position(it, y) }
            }
        }
    }
}
