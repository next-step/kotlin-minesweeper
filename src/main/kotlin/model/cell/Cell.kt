package model.cell

interface Cell {
    fun open()

    data class Fake(
        private val height: Int,
        private val width: Int
    ) : Cell {
        override fun open() {}
    }
}