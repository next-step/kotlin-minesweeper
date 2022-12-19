package domain

data class Rectangle(
    private val width: Width,
    private val height: Height
) {
    fun getWidth(): Int = width.value
    fun getHeight(): Int = height.value

    fun toPositions(): List<Position> {
        return Position.createAll(this)
    }
}
