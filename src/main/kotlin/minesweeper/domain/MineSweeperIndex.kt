package minesweeper.domain

class MineSweeperIndex(val position: Position) {

    fun mineCount(mines: Mines): Int {
        if (mines.isMine(position)) return MINE
        return IndexSquare.values().count { mines.isMine(position + it.position) }
    }

    companion object {
        const val MINE = -1
    }
}
