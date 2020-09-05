package model

data class Cell(val position: Position, var value: Value) {

    fun match(position: Position): Boolean {
        return position == this.position
    }

    fun addCount() {
        value = value.addValue()
    }

    fun isMine(): Boolean {
        return value.isMine()
    }

    fun isZero(): Boolean {
        return value.count == 0
    }

    override fun toString(): String {
        return value.toString()
    }
}
