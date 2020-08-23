package minesweeper.domain

class CityMap(private var streets: Streets, mineCount: Int) {

    init {
        repeat(mineCount) { streets.layMine() }
        streets = streets.mineCounted()
    }

    constructor(height: Int, width: Int, mineCount: Int) : this(
        Streets((0 until height).map { number -> Street(number, width) }),
        mineCount
    )

    override fun toString(): String = streets.toString()
}
