package domain.strategy

import domain.Locations
import domain.MineCount

fun interface CellGenerateStrategy {
    fun generate(locations: Locations, mineCount: MineCount): Locations
}
