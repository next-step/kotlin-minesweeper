package domain.strategy

interface MineAllocationStrategy {

    fun getAssignMineLocation(totalPlaceNumber: Int, numberToAllocate: Int): Set<Int>
}
