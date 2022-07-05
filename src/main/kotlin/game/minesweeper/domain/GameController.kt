package game.minesweeper.domain

import game.minesweeper.domain.map.MapConfig
import game.minesweeper.domain.map.MineMap
import game.minesweeper.domain.strategy.RandomMineGenerator

class GameController(config: MapConfig, private val mineCount: Int) {

    private val _mineMap: MineMap

    init {
        require(config.configurableMine(mineCount)) { "지뢰의 개수가 너무 많습니다.(${mineCount}개)" }
        _mineMap = MineMap.create(config)
        _mineMap.setMines(RandomMineGenerator(config).generate(mineCount))
    }

    fun map() = _mineMap
}
