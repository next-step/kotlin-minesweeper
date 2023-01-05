package domain

enum class GameResult(val label: String) {
    WIN("Win Game!"), LOSE("Lose Game.");

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
