package minesweeper.domain

sealed interface CompletedGame : Game

class PlayerWonGame(
    override val board: PlayerWonBoard,
) : CompletedGame

class MineDetonatedGame(
    override val board: MineDetonatedBoard,
) : CompletedGame
