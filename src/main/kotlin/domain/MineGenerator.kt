package domain

import kotlin.random.Random

object MineGenerator {
    fun create(height: Int, width: Int, count: Int): List<Location> {
        val lists: MutableList<Location> = mutableListOf()
        for (i in 1..count) {
            lists.add(getNewLocation(lists, height, width))
        }
        return lists
    }

    private fun getNewLocation(lists: List<Location>, height: Int, width: Int): Location {
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
