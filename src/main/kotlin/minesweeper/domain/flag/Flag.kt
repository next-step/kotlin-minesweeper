package minesweeper.domain.flag

sealed class Flag {

    abstract fun currentState(): String
}
