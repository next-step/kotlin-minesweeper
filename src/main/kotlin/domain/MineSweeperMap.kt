package domain

import kotlin.random.Random
import kotlin.random.nextInt

class MineSweeperMap(private val property: Property) {
    val value: Array<Array<Cell>>

    init {
        value = initGameMap()
    }

    private fun initGameMap(): Array<Array<Cell>> {
        val minePositionSet = getMinePositionSet()

        return with(property) {
            (MAP_START_INDEX_VALUE..height.value).map { row ->
                (MAP_START_INDEX_VALUE..width.value).map { column ->
                    val position = Position(row.toPositiveNumber(), column.toPositiveNumber())
                    val cellType = if (minePositionSet.contains(position)) CellType.MINE else CellType.NORMAL
                    Cell(position, cellType)
                }.toTypedArray()
            }.toTypedArray()
        }
    }

    private fun getMinePositionSet(): Set<Position> {
        return with(property) {
            val gameMapRange = 0 until height.value * width.value
            val numberSet = mutableSetOf<Position>()

            while (numberSet.size < mineCount.value) {
                val randomNumber = Random.nextInt(gameMapRange)
                val row = randomNumber / width.value + INDEX_VALUE_FOR_CONVENIENCE
                val column = randomNumber % width.value + INDEX_VALUE_FOR_CONVENIENCE
                numberSet.add(Position(row.toPositiveNumber(), column.toPositiveNumber()))
            }

            numberSet.toSet()
        }
    }

    data class Property(
        val height: PositiveNumber,
        val width: PositiveNumber,
        val mineCount: MineCountNumber,
    )

    companion object {
        const val MAP_START_INDEX_VALUE = 1
        const val INDEX_VALUE_FOR_CONVENIENCE = 1
    }
}
