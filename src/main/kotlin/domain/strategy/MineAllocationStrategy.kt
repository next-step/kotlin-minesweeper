package domain.strategy

interface MineAllocationStrategy {

    fun calculate(totalPlaceNumber: Int, numberToAllocate: Int): Set<Int>
}
