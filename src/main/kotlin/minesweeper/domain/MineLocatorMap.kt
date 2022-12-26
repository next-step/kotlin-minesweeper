package minesweeper.domain

import kotlin.random.Random

class MineLocatorMap(private val mapSize: MapSize, countOfMine: Int) {
    val sites: MutableList<Site> = (0 until mapSize.height).flatMap { height ->
        (0 until mapSize.width).map { Site(it, height) }
    }.toMutableList()

    init {
        val size = mapSize.width * mapSize.height
        require(countOfMine in MIN_MINE_COUNT..size) { "count of mine should greater than or equal to $MIN_MINE_COUNT and less than or equal to $size" }
        mine(countOfMine, size)
    }

    constructor(width: Int, height: Int, countOfMine: Int) : this(MapSize(width, height), countOfMine)

    private tailrec fun mine(countOfMine: Int, size: Int) {
        if (countOfMine == 0) {
            return
        }

        val randomSiteIndex = Random.nextInt(size)

        if (sites[randomSiteIndex].hasMine) {
            mine(countOfMine, size)
            return
        }

        sites[randomSiteIndex] = sites[randomSiteIndex].mine()

        mine(countOfMine - 1, size)
    }

    companion object {
        private const val MIN_MINE_COUNT = 1
    }
}
