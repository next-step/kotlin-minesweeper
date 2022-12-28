package domain

import domain.block.Block

data class Row(
    val cells: List<Block>
) {
    fun isAllOpened(): Boolean {
        return cells.none { it.availableOpen() }
    }
}
