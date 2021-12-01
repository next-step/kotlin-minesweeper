package minesweeper.domain

class Height(private val height: Int = DEFAULT_HEIGHT) {
    init {
        if (height < DEFAULT_HEIGHT) {
            throw IllegalArgumentException()
        }
    }

    companion object {
        const val DEFAULT_HEIGHT = 1
    }
}