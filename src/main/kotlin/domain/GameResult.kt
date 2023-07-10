package domain

enum class GameResult {
    WIN, LOSE,
    ;

    fun isWin(): Boolean = this == WIN

    companion object {
        fun of(existsOpenMine: Boolean, isSameCountOfCloseAndMine: Boolean): GameResult {
            return when {
                existsOpenMine -> LOSE
                isSameCountOfCloseAndMine -> WIN
                else -> LOSE
            }
        }
    }
}
