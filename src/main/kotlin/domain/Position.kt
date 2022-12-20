package domain

data class Position(val x: Int, val y: Int) : Comparable<Position> {

    override fun compareTo(other: Position): Int {
        return when {
            this.x != other.x -> this.x compareTo other.x
            this.y != other.y -> this.y compareTo other.y
            else -> 0
        }
    }

    companion object {
        private val START_POSITION: Position = Position(0, 0)

        fun createAll(rectangle: Rectangle, startPosition: Position = START_POSITION): List<Position> {
            return (startPosition.x until rectangle.getWidth()).flatMap { y ->
                (startPosition.y until rectangle.getHeight()).map { Position(it, y) }
            }
        }
    }
}
