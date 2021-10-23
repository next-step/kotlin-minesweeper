package model

enum class State {
    OPEN,
    CLOSE;

    fun closed(): Boolean {
        return this == CLOSE
    }
}