package domain

enum class GameStatus(
    val isProgressing: Boolean
) {

    WIN(false),
    PROGRESSING(true),
    LOSE(false)
}
