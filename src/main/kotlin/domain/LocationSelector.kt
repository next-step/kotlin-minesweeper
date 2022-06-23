package domain

interface LocationSelector {
    fun select(numberOfSelection: Int, locations: List<Location>): List<Location>
}
