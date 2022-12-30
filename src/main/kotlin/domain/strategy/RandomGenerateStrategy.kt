package domain.strategy

import domain.Locations
import domain.MineCount

class RandomGenerateStrategy : CellGenerateStrategy {
    override fun generate(locations: Locations, mineCount: MineCount): Locations {
        return locations.makeRandomLocations(mineCount)
    }
}
