package minesweeper.domain

open class Field {
    protected var status: FieldStatus = FieldStatus.CLOSED

    override fun toString(): String {
        return CLOSED
    }

    fun open() {
        status = FieldStatus.OPEN
    }

    fun isOpened(): Boolean {
        return status == FieldStatus.OPEN
    }

    companion object {
        const val CLOSED = "C"
    }
}

class Mine : Field() {
    override fun toString(): String {
        if (status == FieldStatus.CLOSED) {
            return CLOSED
        }

        return MINE
    }

    companion object {
        private const val MINE = "*"
    }
}

class Safe(val aroundMineCount: Int) : Field() {
    override fun toString(): String {
        if (status == FieldStatus.CLOSED) {
            return CLOSED
        }

        return aroundMineCount.toString()
    }
}
