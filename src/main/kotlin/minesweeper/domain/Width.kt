package minesweeper.domain

data class Width(val value: Int) {

    companion object {
        open fun from(value: Int): Width = Width(value)
    }
}
