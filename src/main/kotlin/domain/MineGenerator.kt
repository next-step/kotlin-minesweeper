package domain

import kotlin.random.Random

class MineGenerator {
    companion object {
        private val lists: MutableList<Location> = mutableListOf()

        fun create(height: Int, width: Int, count: Int): Mines {
            for (i in 1..count) {
                lists.add(getNewLocation(height, width))
            }
            return Mines(lists)
        }

        private fun getNewLocation(height: Int, width: Int): Location {
            var location = Location(getRandomWidth(width), getRandomHeight(height))
            while (lists.contains(location)) {
                location = Location(getRandomWidth(width), getRandomHeight(height))
            }
            return location
        }

        private fun getRandomWidth(width: Int): Int {
            return Random.nextInt(width)
        }

        private fun getRandomHeight(height: Int): Int {
            return Random.nextInt(height)
        }
    }
}