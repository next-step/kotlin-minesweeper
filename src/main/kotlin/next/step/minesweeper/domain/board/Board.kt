package next.step.minesweeper.domain.board

import next.step.minesweeper.domain.mine.MinePosition
import next.step.minesweeper.domain.mine.MinePositions
import next.step.minesweeper.domain.position.Position

data class Board(private val rows: List<BoardRow>, private val area: BoardArea) {

    fun plantMines(positions: MinePositions) {
        require(area.canHave(positions.size)) { "지뢰 개수는 ${area()}개보다 많을 수 없습니다." }
        positions.forEach {
            plantMine(it)
            notifyMine(it)
        }
    }

    private fun plantMine(position: MinePosition) {
        require(area.inHeight(position.y)) { "지뢰 y 위치는 ${height()} 보다 작아야 합니다." }
        require(area.inWidth(position.x)) { "지뢰 x 위치는 ${width()} 보다 작아야 합니다." }
        rows[position.y].plantMine(position.x)
    }

    private fun notifyMine(position: MinePosition) =
        position.nearMinePositions().filter { it in area }.forEach { pointAt(it).notifyMine() }

    private fun pointAt(position: Position) = rows[position.y].pointAt(position.x)

    fun area(): Int = area.area()

    fun width(): Int = area.width()

    fun height(): Int = area.height()

    fun points(): List<List<BoardPoint>> = rows.map { it.points() }

    companion object {

        fun covered(height: Int, width: Int): Board {
            val boardHeight = BoardHeight(height)
            val boardWidth = BoardWidth(width)
            return Board(boardHeight.range().map { BoardRow.covered(boardWidth) }, BoardArea(boardHeight, boardWidth))
        }

        fun mineFree(height: BoardHeight, width: BoardWidth): Board =
            Board(height.range().map { BoardRow.mineFree(width) }, BoardArea(height, width))
    }
}
