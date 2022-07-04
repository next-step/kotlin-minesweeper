package minesweeper.domain

sealed class Cell

object Mine : Cell()

class Opened(counter: Int = 0) : Cell() {

    var counter = counter
        private set

    fun increment() {
        counter += 1
    }

    override fun equals(other: Any?): Boolean {
        return other is Opened && this.counter == other.counter
    }

    override fun toString(): String {
        return counter.toString()
    }

    override fun hashCode(): Int {
        return counter
    }
}
