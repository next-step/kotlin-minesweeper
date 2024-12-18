package minesweeper.domain

class PlayableGame(
    override val board: PlayableBoard,
) : Game {
    fun open(
        y: Int,
        x: Int,
    ): Game =
        when (val newBoard = board.open(y, x)) {
            is PlayableBoard -> PlayableGame(newBoard)
            is PlayerWonBoard -> PlayerWonGame(newBoard)
            is MineDetonatedBoard -> MineDetonatedGame(newBoard)
        }
}
