package minesweeper.domain

@JvmInline
value class Cells(val values: List<Cell>) {
    val size: Int
        get() = values.size

    operator fun plus(other: Cells): Cells {
        return Cells(values + other.values)
    }

    fun shuffled(): Cells {
        return Cells(values.shuffled())
    }

    fun chunked(width: Int): List<Cells> {
        return values.chunked(width).map { Cells(it) }
    }

    companion object {
        fun normal(size: Int): Cells {
            return Cells(List(size) { Normal() })
        }

        fun mine(size: Int): Cells {
            return Cells(List(size) { Mine() })
        }
    }
}
