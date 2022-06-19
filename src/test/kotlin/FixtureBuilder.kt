import domain.strategy.MineAllocationStrategy

class FixtureBuilder {

    companion object {
        class MineAllocator(private val manual: Set<Int>) : MineAllocationStrategy {
            override fun calculate(totalPlaceNumber: Int, numberToAllocate: Int): Set<Int> {
                return manual
            }
        }
    }
}
