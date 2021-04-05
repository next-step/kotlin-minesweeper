package minesweeper.domain.gameboard

sealed class Block(var value: String) {
    class Mine() : Block(" * ")
    class Covered() : Block(" C ")
    class Number() : Block(" ")

    fun isMine(): Boolean {
        return this is Mine
    }
}
