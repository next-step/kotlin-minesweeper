package domain.game

import domain.MineSweeperInitProperty

interface MineSweeperGameFactory {

    fun create(mineSweeperInitProperty: MineSweeperInitProperty): MineSweeperGame
}
