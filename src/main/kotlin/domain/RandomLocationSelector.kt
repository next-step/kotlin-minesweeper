package domain

object RandomLocationSelector : LocationSelector {
    override fun select(number: Int, locations: List<Location>): List<Location> {
        require(number >= 0) {}
        return locations.shuffled().take(number)
    }
}
