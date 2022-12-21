package domain

data class Position(val x: Int, val y: Int) : Comparable<Position> {

    override fun compareTo(other: Position): Int {
        return when {
            this.x != other.x -> this.x compareTo other.x
            this.y != other.y -> this.y compareTo other.y
            else -> 0
        }
    }
}
