package domain

import domain.strategy.MineAllocationStrategy

class MineAllocator : MineAllocationStrategy {

    override fun calculate(totalPlaceNumber: Int, numberToAllocate: Int): Set<Int> {
        val sliceRange = IntRange(0, numberToAllocate - 1)
        return IntRange(0, totalPlaceNumber - 1)
            .shuffled()
            .slice(sliceRange)
            .toSet()
    }
}
