package domain.strategy

import domain.Place

interface MineAllocationStrategy {

    fun getAssignMineLocation(totalPlaceNumber: Int, numberToAllocate: Int): Set<Place>
}
