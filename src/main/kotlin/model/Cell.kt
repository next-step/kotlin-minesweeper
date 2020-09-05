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

    override fun toString(): String {
        return value.toString()
    }
}
