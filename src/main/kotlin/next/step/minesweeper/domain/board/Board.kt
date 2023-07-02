package next.step.minesweeper.domain.board

import next.step.minesweeper.domain.mine.MinePosition
import next.step.minesweeper.domain.mine.MinePositions
import next.step.minesweeper.domain.position.Position

@JvmInline
value class Board(private val rows: List<BoardRow>) {

    fun plantMines(positions: MinePositions) {
        require(positions.size <= area()) { "지뢰 개수는 ${area()}개보다 많을 수 없습니다." }
        positions.forEach {
            plantMine(it)
            notifyMine(it)
        }
    }

    private fun plantMine(position: MinePosition) {
        require(position.y < height()) { "지뢰 y 위치는 ${height()} 보다 작아야 합니다." }
        rows[position.y].plantMine(position.x)
    }

    private fun notifyMine(position: MinePosition) =
        position.nearMinePositions().filter { it in this }.forEach { pointAt(it).notifyMine() }

    private fun pointAt(it: Position) = rows[it.y].pointAt(it.x)

    operator fun contains(position: Position): Boolean = inHeight(position) && inWidth(position.x)

    private fun inHeight(position: Position) = BoardHeight(height()).inRange(position)

    private fun inWidth(x: Int) = BoardWidth(width()).inRange(x)

    fun area(): Int = width() * height()

    fun width(): Int = rows.firstOrNull()?.size() ?: 0

    fun height(): Int = rows.size

    fun points(): List<List<BoardPoint>> = rows.map { it.points() }

    companion object {

        fun covered(height: Int, width: Int): Board =
            Board(BoardHeight(height).range().map { BoardRow.covered(BoardWidth(width)) })

        fun mineFree(height: BoardHeight, width: BoardWidth): Board =
            Board(height.range().map { BoardRow.mineFree(width) })
    }
}
