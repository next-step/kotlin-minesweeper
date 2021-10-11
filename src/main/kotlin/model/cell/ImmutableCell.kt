package model.cell

import model.CellIdentity
import model.State

data class ImmutableCell(
    private val cellIdentity: CellIdentity,
    private var state: State
): Cell {
    override fun open() {
        state = State.OPEN
    }
}