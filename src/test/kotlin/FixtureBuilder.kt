import domain.Place
import domain.strategy.MineAllocationStrategy

class FixtureBuilder {

    companion object {
        class MineAllocator(private val manual: Set<Place>) : MineAllocationStrategy {
            override fun getAssignMineLocation(totalPlaceNumber: Int, numberToAllocate: Int): Set<Place> {
                return manual
            }
        }
    }
}
