package minesweeper.model

data class Block(var type: Type = Type.ZERO) {
    fun setMine() {
        type = Type.MINE
    }

    fun setCount(count: Int) {
        type = Type.values().filter { it.count == count }[0]
    }
}
