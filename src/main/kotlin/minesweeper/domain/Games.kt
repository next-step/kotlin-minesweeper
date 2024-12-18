package minesweeper.domain

sealed interface Game {
    val board: Board
}

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

sealed interface CompletedGame : Game

class PlayerWonGame(
    override val board: PlayerWonBoard,
) : CompletedGame

class MineDetonatedGame(
    override val board: MineDetonatedBoard,
) : CompletedGame
