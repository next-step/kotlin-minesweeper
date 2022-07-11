package minesweeper.domain

enum class MineSweeperResult(
    val message: String
) {
    WIN("Win Game."),
    LOSE("Lose Game."),
    ;
}
