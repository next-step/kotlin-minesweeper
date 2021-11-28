package minesweeper.domain

data class Board(val area: Area, val blocks: List<Block>) {
    init {
        require(blocks.count { it is Mine } > MINIMUM_MINE_COUNT)
        require(area.getArea() == blocks.size)
    }

    companion object {
        private const val MINIMUM_MINE_COUNT = 0
    }
}
