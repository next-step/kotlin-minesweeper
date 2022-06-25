package domain

import domain.geometric.Location

interface LocationSelector {
    fun select(numberOfSelection: Int, locations: List<Location>): List<Location>
}

object RandomLocationSelector : LocationSelector {

    private const val MIN_SELECTION = 1

    override fun select(numberOfSelection: Int, locations: List<Location>): List<Location> {
        require(numberOfSelection >= MIN_SELECTION) {
            "위치는 최소 $MIN_SELECTION 이상 선택되어야 합니다. 입력된 위치 개수 = $numberOfSelection"
        }
        return locations.shuffled()
            .take(numberOfSelection)
    }
}
