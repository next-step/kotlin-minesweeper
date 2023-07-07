package domain.game

import domain.cell.Cell

sealed class OpenResult {

    abstract val isGameFinished: Boolean

    object MineOpened : OpenResult() {

        override val isGameFinished = true
    }

    data class GroundOpened(val cells: List<List<Cell>>) : OpenResult() {

        override val isGameFinished = false
    }

    object AllMineFound : OpenResult() {

        override val isGameFinished = true
    }
}
