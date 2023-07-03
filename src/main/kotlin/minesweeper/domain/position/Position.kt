package minesweeper.domain.position

data class Position(private val x: Int, private val y: Int) {

    init {
        require(x >= MIN_POSITION) { "열(x) 값은 $MIN_POSITION 이상이어야 합니다." }
        require(y >= MIN_POSITION) { "행(y) 값은 $MIN_POSITION 이상이어야 합니다." }
    }

    fun aroundPositions(): Positions = Positions(
        Direction.values().mapNotNull {
            try {
                this.move(it)
            } catch (exception: IllegalArgumentException) {
                null
            }
        },
    )

    private fun move(direction: Direction): Position {
        val movedXPosition = direction.moveXPosition(x)
        val movedYPosition = direction.moveYPosition(y)
        return Position(x = movedXPosition, y = movedYPosition)
    }

    companion object {
        const val MIN_POSITION = 1
    }
}
