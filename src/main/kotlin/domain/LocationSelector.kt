package domain

import domain.geometric.Location

interface LocationSelector {
    fun select(numberOfSelection: Int, locations: List<Location>): List<Location>
}

object RandomLocationSelector : LocationSelector {

    private const val MIN_SELECTION = 0

    override fun select(numberOfSelection: Int, locations: List<Location>): List<Location> {
        require(numberOfSelection >= MIN_SELECTION) {}
        return locations.shuffled()
            .take(numberOfSelection)
    }
}
