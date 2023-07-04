package minesweeper.domain

@JvmInline
value class Cells(val values: List<Cell>) : List<Cell> by values {

    companion object {
        fun normal(size: Int): Cells {
            return Cells(List(size) { Normal() })
        }

        fun mine(size: Int): Cells {
            return Cells(List(size) { Mine() })
        }
    }
}
