package domain

import domain.strategy.MineAllocationStrategy

class Minesweeper(minesweeperProperty: MinesweeperProperty, mineAllocationStrategy: MineAllocationStrategy) {

    private val _board: MutableList<Row>

    val board: List<Row>
        get() = _board

    init {
        val totalPlaceNumber = minesweeperProperty.width * minesweeperProperty.height
        val numberToAllocate = minesweeperProperty.mineCount

        val minePlaceNumbers = mineAllocationStrategy
            .getAssignMineLocation(totalPlaceNumber, numberToAllocate)
            .map { it.number }

        _board = MutableList(minesweeperProperty.height) { rowIndex ->
            val places = mutableListOf<Place>()

            for (col in 1..minesweeperProperty.width) {
                val placeNumber = (rowIndex * minesweeperProperty.height) + col
                val placeType = getPlaceType(minePlaceNumbers, placeNumber)

                val place = Place(placeNumber, placeType)
                places.add(place)
            }

            Row(places)
        }

        calculateNearMineCount()
    }

    private fun calculateNearMineCount() {
        _board.forEachIndexed { rowIndex, row ->
            row.transformPlaceIndexed(createMineCountedPlace(rowIndex))
        }
    }

    private fun getNearMineCount(row: Int, col: Int): Int {
        var count = 0

        if (isMineInPlace(row - 1, col - 1)) count++
        if (isMineInPlace(row - 1, col)) count++
        if (isMineInPlace(row - 1, col + 1)) count++
        if (isMineInPlace(row, col - 1)) count++
        if (isMineInPlace(row, col + 1)) count++
        if (isMineInPlace(row + 1, col - 1)) count++
        if (isMineInPlace(row + 1, col)) count++
        if (isMineInPlace(row + 1, col + 1)) count++

        return count
    }

    private fun isMineInPlace(row: Int, col: Int): Boolean {
        return getPlaceOrNull(row, col)?.isMine() ?: false
    }

    private fun getPlaceOrNull(row: Int, col: Int): Place? {
        return _board.getOrNull(row)?.places?.getOrNull(col)
    }

    private fun createMineCountedPlace(rowIndex: Int): (Int, Place) -> Place {
        return { colIndex, place -> getCreatedPlace(place, rowIndex, colIndex) }
    }

    private fun getCreatedPlace(place: Place, rowIndex: Int, colIndex: Int): Place {
        if (place.isNotMine()) {
            return Place(place.number, place.placeType, getNearMineCount(rowIndex, colIndex))
        }

        return place
    }

    private fun getPlaceType(minePlaceNumbers: List<Int>, placeNumber: Int): PlaceType {
        if (minePlaceNumbers.contains(placeNumber)) {
            return PlaceType.MINE
        }

        return PlaceType.NOT_MINE
    }
}
