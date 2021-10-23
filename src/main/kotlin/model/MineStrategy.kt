package model

interface MineStrategy {
    fun isMineIterator(totalCellCount: Int, mineCount: Int): Iterator<Boolean>
}
