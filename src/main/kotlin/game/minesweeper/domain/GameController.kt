package game.minesweeper.domain

import game.minesweeper.domain.map.Row

class GameController(private val height: Int, private val width: Int, private val mineCount: Int) {

    val rows: List<Row>

    init {
        require(height > 0 && width > 0) { "높이와 너비는 0 보다 커야 합니다." }
        require(height * width >= mineCount) { "지뢰의 개수가 너무 많습니다.(${mineCount}개)" }
        rows = createMap()
    }

    private fun createMap(): List<Row> = (START_ROW_NUM..height).map { Row.from(it, width) }

    companion object {
        private const val START_ROW_NUM = 1
    }
}
