package domain

class MineBoard(private val mineBoard: Map<Position, Block>) {
    fun getGroupingMap(): Map<Int, List<Block>> {
        return mineBoard.entries.groupBy({ it.key.getX() }, { it.value })
    }
}
