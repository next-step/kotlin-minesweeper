package domain

class Locations(val values: List<Int>) {
    operator fun minus(other: Locations): Locations {
        val result = this.values - other.values.toSet()

        return Locations(result)
    }

    fun makeRandomLocations(mineCount: MineCount): Locations {
        val list = values.shuffled().take(mineCount.value)

        return Locations(list)
    }
}
