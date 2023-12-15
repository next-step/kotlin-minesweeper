package view

sealed class GameState {
    fun play(): Playing {
        check(this is Playable)
        return Playing
    }
    fun isPlaying(): Boolean = this == Playing

    sealed class Playable : GameState()
    object Initial : Playable()
    object Playing : Playable()
    sealed class GameOver : GameState()
    object Win : GameOver()
    object Lose : GameOver()
}
