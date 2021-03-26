package minesweeper.domain

data class Cell(val bomb: Boolean = false, val count: Int = 0) {
    lateinit var link: List<Cell>
    var open: Boolean = false
        private set

    var exploded: Boolean = false
        private set

    fun open() {
        if (bomb) {
            exploded = true
            open = true
            return
        }

        open = true

        if (count > 0) {
            return
        }

        link.filter { it.canOpen() }
            .forEach { it.open() }
    }

    fun quietlyOpen() {
        open = true
    }

    private fun canOpen(): Boolean = !(bomb || open)
}
