package next.step.minesweeper.domain.board

import next.step.minesweeper.domain.position.Position

@JvmInline
value class BoardWidth(private val width: Int) {

    init {
        require(width > MIN_WIDTH) { "너비는 ${MIN_WIDTH}보다 커야합니다." }
    }

    fun inRange(position: Position) = position.x in range()

    fun range(): IntRange = MIN_WIDTH until width

    companion object {
        const val MIN_WIDTH = 0
    }
}
