package domain

import kotlin.random.nextInt

class RandomMinePositionGenerator private constructor(private val property: Property) : MinePositionGenerator {

    override fun generate(): Positions {
        return with(property.mapProperty) {
            val gameMapRange = 0 until height.value * width.value
            val numberSet = mutableSetOf<Position>()

            while (numberSet.size < property.mineCountNumber.value) {
                val randomNumber = kotlin.random.Random.nextInt(gameMapRange)
                val row = randomNumber / width.value + MineSweeperMap.INDEX_VALUE_FOR_CONVENIENCE
                val column = randomNumber % width.value + MineSweeperMap.INDEX_VALUE_FOR_CONVENIENCE
                numberSet.add(Position.of(row, column))
            }

            numberSet.toList()
        }.toPositions()
    }

    data class Property(
        val mapProperty: MineSweeperMap.Property,
        val mineCountNumber: MineCountNumber,
    )

    companion object {
        fun of(mapProperty: MineSweeperMap.Property, mineCountNumber: MineCountNumber): RandomMinePositionGenerator {
            val generatorProperty = Property(mapProperty, mineCountNumber)
            return RandomMinePositionGenerator(generatorProperty)
        }
    }
}
