package domain

fun GameResult.isWin(): Boolean = this == GameResult.WIN
enum class GameResult {
    WIN, LOSE,
    ;

    companion object {
        fun valueOf(existsOpenMine: Boolean, isSameCountOfCloseAndMine: Boolean): GameResult {
            return when {
                existsOpenMine -> LOSE
                isSameCountOfCloseAndMine -> WIN
                else -> LOSE
            }
        }
    }
}
