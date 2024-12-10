package domain

class MineSet(val mines: Set<Mine>) {
    fun getMineMap(): MineMap {
        return MineMap(mines.associate { Pair(MinePosition(it.getXPosition(), it.getYPosition()), true) })
    }
}
