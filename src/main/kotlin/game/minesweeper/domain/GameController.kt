package game.minesweeper.domain

import game.minesweeper.domain.map.MapConfig
import game.minesweeper.domain.map.MineMap
import game.minesweeper.domain.strategy.RandomMineGenerator

class GameController(config: MapConfig, private val mineCount: Int) {

    val mineMap: MineMap

    init {
        require(config.configurableMine(mineCount)) { "지뢰의 개수가 너무 많습니다.(${mineCount}개)" }
        mineMap = MineMap.create(config)
        mineMap.setMines(RandomMineGenerator(config).generate(mineCount))
    }
}
