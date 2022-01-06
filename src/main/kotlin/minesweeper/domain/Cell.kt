package minesweeper.domain

sealed class Cell {

    abstract val position: Position
    abstract val openState: OpenState

    abstract fun isMine(): Boolean
    abstract fun open(): Cell
    abstract fun isOpen(): Boolean

    fun filterAroundCellPositions(safetyCellPositions: List<Position>): List<Position> {
        return aroundPositions().filter { safetyCellPositions.contains(it) }
    }

    fun aroundMineCells(mineCells: List<MineCell>): List<MineCell> {
        return mineCells.filter { aroundPositions().contains(it.position) }
    }

    fun aroundSafetyCells(safetyCells: List<SafetyCell>): List<SafetyCell> {
        return safetyCells.filter { aroundPositions().contains(it.position) }
    }

    fun isRowStartCell(): Boolean = position.isRowStart()

    private fun aroundPositions(): List<Position> = position.aroundPositionList()
}
