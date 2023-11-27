package business

enum class GameStatus {
    WIN, GAME_OVER, CONTINUE;

    fun isContinue(): Boolean = this == CONTINUE
}
