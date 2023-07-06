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

    private fun getNewLocation(lists: MutableList<Location>, height: Int, width: Int): Location {
        var location = Location(getRandomHeight(height), getRandomWidth(width))
        while (lists.contains(location)) {
            location = Location(getRandomHeight(height), getRandomWidth(width))
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
