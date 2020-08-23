package minesweeper.model.block

import minesweeper.model.Type

data class Block(var type: Type = Type.from(Type.EMPTY.count)) {
    fun setMine() {
        type = Type.from(Type.MINE.count)
    }

    fun setCount(count: Int) {
        type = Type.from(count)
    }
}
