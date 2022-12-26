package minesweeper.domain

data class Site(private val coordinate: Coordinate, val hasMine: Boolean = false) {

    constructor(x: Int, y: Int) : this(Coordinate(x, y))

    fun mine() = Site(coordinate, true)
}
