package domain.strategy

import domain.Fields
import domain.MineCnt

interface BoardGenerateStrategy {
    fun generate(maxCoordinateSize: Int, mineCnt: MineCnt): Fields
}
