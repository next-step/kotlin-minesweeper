package domain

interface MinePositionsFactory {
    fun create(minesweeperInfo: MinesweeperInfo): MinePositions
}
