package domain

class Locations(val values: List<Int>) {
    operator fun minus(other: Locations): Locations {
        val result = this.values - other.values.toSet()

        return Locations(result)
    }

    companion object {
        fun from(cellSize: Int, list: List<Int>): Locations {
            val range = List(cellSize) { it }
            if (!range.containsAll(list)) throw IndexOutOfBoundsException()

            return Locations(list)
        }
    }
}
