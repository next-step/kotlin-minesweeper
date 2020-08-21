package model

class Square(val width: Line, val height: Line) {
    fun make(): MinePlate {
        val rows = Array(height.value) { Array(width.value) { "C" } }
        return MinePlate(rows)
    }
}
