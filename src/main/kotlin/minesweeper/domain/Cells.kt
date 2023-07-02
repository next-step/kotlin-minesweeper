package minesweeper.domain

@JvmInline
value class Cells(val values: List<Cell>) {
    operator fun plus(other: Cells): Cells {
        return Cells(values + other.values)
    }

    fun shuffled(): Cells {
        return Cells(values.shuffled())
    }

    fun chunked(width: Int): List<Cells> {
        return values.chunked(width).map { Cells(it) }
    }

    fun forEach(action: (Cell) -> Unit) {
        values.forEach(action)
    }

    companion object {
        fun empty(size: Int): Cells {
            return Cells(List(size) { Cell() })
        }

        fun mine(size: Int): Cells {
            return Cells(List(size) { Cell(isMine = true) })
        }
    }
}
