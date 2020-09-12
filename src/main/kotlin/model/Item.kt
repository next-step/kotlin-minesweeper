package model

data class Item(val position: Position, var type: Type) {
    var isOpen: Boolean = false
        private set

    fun isOpen() {
        isOpen = true
    }

    override fun toString(): String {
        if (isOpen) return type.toString()
        return Type.NONE.toString()
    }
}
