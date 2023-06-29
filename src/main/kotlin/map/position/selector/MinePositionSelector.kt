package map.position.selector

import map.position.Position

fun interface MinePositionSelector {
    fun select(): Position
}