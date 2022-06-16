package minesweeper.domain.board

import kotlin.properties.Delegates

class BoardBuilder {
    private var width: Int by Delegates.notNull()
    private var height: Int by Delegates.notNull()

    fun width(value: Int) {
        width = value
    }

    fun height(value: Int) {
        height = value
    }

    fun build() = Board(width, height)
}
