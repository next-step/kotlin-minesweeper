import domain.Place
import domain.strategy.MineAllocationStrategy

class FixtureBuilder {

    companion object {
        class MineAllocatorFixture(private val manual: Set<Place>) : MineAllocationStrategy {
            override fun getAssignMineLocation(totalPlaceNumber: Int, numberToAllocate: Int): Set<Place> {
                return manual
            }
        }
    }
}
