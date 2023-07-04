package domain.game

import domain.MineSweeperInitProperty
import domain.map.MineMapFactory

class RealMineSweeperGameFactory(
    private val mineMapFactory: MineMapFactory
) : MineSweeperGameFactory {

    override fun create(mineSweeperInitProperty: MineSweeperInitProperty): MineSweeperGame {
        val mineMap = mineMapFactory.create(mineSweeperInitProperty)
        return MineSweeperGame(mineMap)
    }
}
