package domain

open class Coordinate(val x: Int, val y: Int) : Comparable<Coordinate> {
    init {
        require(x >= MIN_X && y >= MIN_Y) { "x 좌표는 최소 ${MIN_X}, y 좌표는 최소 $MIN_Y 이상을 만족해야합니다." }
    }

    companion object {
        private const val MIN_X = 0
        private const val MIN_Y = 0
    }

    override fun compareTo(other: Coordinate): Int {
        return if (this.y != other.y) {
            this.y - other.y
        } else {
            this.x - other.x
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Coordinate

        if (x != other.x) return false
        if (y != other.y) return false

        return true
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        return result
    }
}
