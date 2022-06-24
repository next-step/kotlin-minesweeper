package domain

import domain.strategy.MineAllocationStrategy

class MineAllocator : MineAllocationStrategy {

    override fun getAssignMineLocation(totalPlaceNumber: Int, numberToAllocate: Int): Set<Place> {
        val sliceRange = IntRange(0, numberToAllocate - 1)
        return IntRange(0, totalPlaceNumber - 1)
            .shuffled()
            .slice(sliceRange)
            .map { Place(it, PlaceType.MINE) }
            .toSet()
    }
}
