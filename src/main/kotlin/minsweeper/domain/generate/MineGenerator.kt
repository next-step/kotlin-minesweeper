package minsweeper.domain.generate

import minsweeper.domain.Coordinate

interface MineGenerator {

    fun generate(): List<Coordinate>

}