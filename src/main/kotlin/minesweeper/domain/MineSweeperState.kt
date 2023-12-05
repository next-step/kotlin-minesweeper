package minesweeper.domain

enum class MineSweeperState(val isEnd: Boolean) {
    WIN(true),
    LOSE(true),
    CONTINUE(false)
}
