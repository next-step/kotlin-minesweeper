package minesweeper.domain

data class Coordinate(val axisX: Int, val axisY: Int) {
    fun itIt(coordinate: Coordinate): Boolean {
        return this == coordinate
    }

    fun isX(lastX: Int): Boolean {
        return this.axisX == lastX
    }
}
