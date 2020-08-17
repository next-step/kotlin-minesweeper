package minesweeper.domain

data class Height(val value: Int) {

    companion object {
        fun from(value: Int): Height = Height(value)
    }
}
