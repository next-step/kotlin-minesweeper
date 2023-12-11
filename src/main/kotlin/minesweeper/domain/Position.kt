package minesweeper.domain

data class Position(val x: Int, val y: Int) {
    fun around(): List<Position> = Direction.eightWays.map { (dx, dy) ->
        Position(x + dx, y + dy)
    }
}
