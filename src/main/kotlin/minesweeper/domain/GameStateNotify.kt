package minesweeper.domain

interface GameStateNotify {

    fun getOpenPosition(): MinePosition

    fun isContinueGame(): Boolean = false

    fun showGameState(status: GameStatus)

    fun showMineMapInProgress(mineMap: MinesMap)
}
