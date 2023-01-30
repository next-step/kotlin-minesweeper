package domain

enum class GameResult {
    WIN, LOSE;

    companion object {
        fun of(isClear: Boolean): GameResult {
            return if (isClear) {
                WIN
            } else {
                LOSE
            }
        }
    }
}
