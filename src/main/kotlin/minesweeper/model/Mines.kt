package minesweeper.model

class Mines(value: Set<Cell>) : Set<Cell> by value {
    companion object {
        fun of(value: List<Cell>): Mines {
            return Mines(value.toSet())
        }
    }
}
