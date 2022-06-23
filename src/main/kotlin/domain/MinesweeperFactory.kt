package domain

interface MinesweeperFactory {
    fun create(minesweeperInfo: MinesweeperInfo): Minesweeper
}
