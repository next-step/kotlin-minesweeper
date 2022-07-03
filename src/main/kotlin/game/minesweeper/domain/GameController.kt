package game.minesweeper.domain

import game.minesweeper.domain.map.MapConfig
import game.minesweeper.domain.map.Row

class GameController(private val config: MapConfig, private val mineCount: Int) {

    val rows: List<Row>

    init {
        require(config.configurableMine(mineCount)) { "지뢰의 개수가 너무 많습니다.(${mineCount}개)" }
        rows = createMap()
    }

    private fun createMap(): List<Row> = (START_ROW_NUM..config.height).map { Row.from(it, config.width) }

    companion object {
        private const val START_ROW_NUM = 1
    }
}
