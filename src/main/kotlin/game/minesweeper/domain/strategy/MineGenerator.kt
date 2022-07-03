package game.minesweeper.domain.strategy

import game.minesweeper.domain.map.Coordinate

fun interface MineGenerator {
    fun generate(count: Int): List<Coordinate>
}
