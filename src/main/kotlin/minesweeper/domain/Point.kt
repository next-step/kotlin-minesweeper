package minesweeper.domain

open class Point(val x: Int, val y: Int) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Point

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
class MinePoint(x: Int, y: Int) : Point(x, y)
class ClearPoint(x: Int, y: Int) : Point(x, y)
