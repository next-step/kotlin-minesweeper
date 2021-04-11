package domain

enum class GameState {
    RUNNING, WIN, LOSE;

    fun isRunning(): Boolean {
        return this === RUNNING
    }
}
