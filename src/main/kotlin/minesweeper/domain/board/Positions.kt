package minesweeper.domain.board

class Positions(private val positions: List<Position>) : List<Position> by positions {

    fun getRandomPositions(count: Int): Positions {
        require(positions.size >= count) { OVER_COUNT_MESSAGE }
        return Positions(this.shuffled().take(count))
    }

    companion object {
        fun of(boardSize: BoardSize): Positions =
            (1..boardSize.height).map { x ->
                (1..boardSize.width).map { y ->
                    Position.of(x, y)
                }
            }.reduce { acc, list ->
                acc + list
            }.run {
                Positions(this)
            }

        const val OVER_COUNT_MESSAGE = "카운트 수가 전체 수보다 큽니다."
    }
}
