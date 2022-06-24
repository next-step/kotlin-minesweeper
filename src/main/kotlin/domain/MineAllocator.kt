package domain

import domain.strategy.MineAllocationStrategy

class MineAllocator : MineAllocationStrategy {

    override fun getAssignMineLocation(totalPlaceNumber: Int, numberToAllocate: Int): Set<Place> {
        return IntRange(0, totalPlaceNumber - 1)
            .shuffled()
            .take(numberToAllocate)
            .map { Place(it, PlaceType.MINE) }
            .toSet()
    }
}
