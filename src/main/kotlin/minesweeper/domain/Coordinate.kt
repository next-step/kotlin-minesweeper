package minesweeper.domain

data class Coordinate(val x: Int, val y: Int) : Comparable<Coordinate> {

    init {
        require(x >= 0 && y >= 0) { "x,y 좌표는 0과 같거나 커야한다." }
    }

    override fun compareTo(other: Coordinate): Int {
        val compareY = y.compareTo(other.y)
        return if (compareY == 0) x.compareTo(other.x)
        else compareY
    }
}
