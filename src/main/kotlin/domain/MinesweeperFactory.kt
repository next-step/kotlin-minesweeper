package domain

interface MinesweeperFactory {
    fun create(minesweeperStartInfo: MinesweeperStartInfo): Minesweeper
}
