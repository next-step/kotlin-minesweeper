package model

data class Cell(val position: Position, var value: Value) {

    fun addCount() {
        value = Value.plusValue(value)
    }

    fun isMine(): Boolean {
        return value == Value.MINE
    }

    fun match(position: Position): Boolean {
        return position == this.position
    }

    override fun toString(): String {
        return value.string
    }
}
