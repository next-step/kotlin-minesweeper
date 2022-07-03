package game.minesweeper.domain

import game.minesweeper.domain.map.MapConfig
import game.minesweeper.domain.map.MineMap

class GameController(private val config: MapConfig, private val mineCount: Int) {

    private val _mineMap: MineMap

    init {
        require(config.configurableMine(mineCount)) { "지뢰의 개수가 너무 많습니다.(${mineCount}개)" }
        _mineMap = MineMap.create(config)
    }

    fun map() = _mineMap
}
