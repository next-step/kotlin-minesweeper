package next.step.minesweeper.domain.board

import next.step.minesweeper.domain.position.Position

@JvmInline
value class BoardHeight(private val height: Int) {

    init {
        require(height > MIN_HEIGHT) { "높이는 ${MIN_HEIGHT}보다 커야합니다." }
    }

    fun range(): IntRange = MIN_HEIGHT until height

    fun inRange(position: Position) = position.y in range()

    companion object {
        const val MIN_HEIGHT = 0
    }
}
