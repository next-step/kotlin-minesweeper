package domain.strategy

import domain.Cell

interface MineCellGenerator {
    fun execute(): List<Cell>
}
