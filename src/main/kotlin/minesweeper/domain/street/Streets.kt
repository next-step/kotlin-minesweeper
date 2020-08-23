package minesweeper.domain.street

class Streets(private val streets: List<Street>) {

    constructor(vararg streets: Street) : this(streets.toList())

    fun layMine(
        streetChoosingStrategy: StreetChoosingStrategy = RandomStreetChoosingStrategy,
        positionChoosingStrategy: PositionChoosingStrategy = RandomPositionChoosingStrategy
    ) {
        var number = getStreetNumber(streetChoosingStrategy)
        var isFullOfMines = streets[number].setMinePosition(positionChoosingStrategy)

        while (isFullOfMines) {
            number = getStreetNumber(streetChoosingStrategy)
            isFullOfMines = streets[number].setMinePosition(positionChoosingStrategy)
        }
    }

    private fun getStreetNumber(streetChoosingStrategy: StreetChoosingStrategy): Int =
        streetChoosingStrategy.getStreetNumber(streets.size)

    fun mineCounted(): Streets {
        streets.forEach { street ->
            // 좌우 지뢰 개수 더하기
            street.addMineCountAround(street.spots)

            // 위 아래 인접한 거리에 있는 지뢰 개수 더하기
            val downNumber = street.streetNumber - 1
            val upperNumber = street.streetNumber + 1

            if (0 <= downNumber)
                street.addMineCountAround(streets[downNumber].spots)

            if (upperNumber < streets.size)
                street.addMineCountAround(streets[upperNumber].spots)
        }
        return Streets(streets)
    }

    fun totalMineCount() = streets.map { it.mineCount() }.sumBy { it }

    operator fun get(id: Int): Street = streets[id]

    override fun toString(): String = streets.joinToString("\n")
}
