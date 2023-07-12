package minesweeper.domain

enum class MineBoardStatus(
    val isEnd: Boolean,
) {
    IN_PROGRESS(false),
    LOSE(true),
    WIN(true),
}
