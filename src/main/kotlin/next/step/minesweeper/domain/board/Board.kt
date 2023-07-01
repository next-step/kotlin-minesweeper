package next.step.minesweeper.domain.board

import next.step.minesweeper.domain.mine.MinePosition
import next.step.minesweeper.domain.mine.MinePositions

@JvmInline
value class Board(private val rows: List<BoardRow>) {

    fun area(): Int = width() * height()

    fun width(): Int = rows.firstOrNull()?.size() ?: 0

    fun height(): Int = rows.size

    fun plantMines(positions: MinePositions) {
        require(positions.size <= area()) { "지뢰 개수는 ${area()}개보다 많을 수 없습니다." }
        positions.map { plantMine(it) }
    }

    private fun plantMine(position: MinePosition) {
        require(position.y < height()) { "지뢰 y 위치는 ${height()} 보다 작아야 합니다." }
        rows[position.y].plantMine(position)
    }

    fun points(): List<List<BoardPoint>> = rows.map { it.points }

    companion object {

        fun covered(height: Int, width: Int): Board = covered(BoardHeight(height), BoardWidth(width))

        fun covered(height: BoardHeight, width: BoardWidth): Board =
            Board(height.range().map { BoardRow.covered(width) })
    }
}
