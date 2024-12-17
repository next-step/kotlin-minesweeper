package view

import domain.MineSweeperMap

interface DrawPolicy {
    fun draw(
        map: MineSweeperMap,
        index: Int,
    )
}
