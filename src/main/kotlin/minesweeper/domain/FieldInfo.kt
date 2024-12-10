package minesweeper.domain

class FieldInfo(private val height: FieldHeight, private val width: FieldWidth) {
    fun getHeight(): Int {
        return height.height
    }

    fun getWidth(): Int {
        return width.width
    }
}
