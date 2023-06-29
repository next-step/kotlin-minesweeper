package tdd.minesweeper.domain.type

enum class GameProgressStatus(val isContinuable: Boolean) {
    WIN(false),
    LOSE(false),
    CONTINUE(true),
    CREATED(true);
}
