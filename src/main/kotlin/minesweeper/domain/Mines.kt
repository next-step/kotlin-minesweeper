package minesweeper.domain

class Mines(
    mineList: List<Mine>
) {
    val positionList: List<Position> = mineList.map { it.position }

    fun hasPosition(position: Position): Boolean = this.positionList.contains(position)
}
