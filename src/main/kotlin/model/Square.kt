package model

class Square(private val width: Line, private val height: Line) {
    fun make(): MinePlate {
        val rows = Array(height.value) { Array(width.value) { "C" } }
        return MinePlate(rows)
    }
}
