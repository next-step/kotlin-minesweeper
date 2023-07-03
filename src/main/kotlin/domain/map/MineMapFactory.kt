package domain.map

import domain.MineSweeperInitProperty

interface MineMapFactory {

    fun create(mineSweeperInitProperty: MineSweeperInitProperty): MineMap
}
