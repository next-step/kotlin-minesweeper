package domain

data class Coordinate(val x: Int, val y: Int) : Comparable<Coordinate> {
    override fun compareTo(other: Coordinate): Int {
        return if (this.x == other.x) this.y.compareTo(other.y) else this.x.compareTo(other.x)
    }
}
