package minesweeper2.domain

interface GameStateNotify {

    fun getOpenPosition(): MinePosition

    fun showGameState(isWin: Boolean)

    fun showMineMapInProgress(mineMap: List<TileRow>)
}
