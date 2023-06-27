package minesweeper_refactor.domain.board.builder

import kotlin.properties.Delegates
import minesweeper_refactor.domain.board.BoardSize

class BoardSizeBuilder : DslBuilder<BoardSize>() {

    private var width by Delegates.notNull<Int>()
    private var height by Delegates.notNull<Int>()

    fun width(value: Int) {
        width = value
    }

    fun height(value: Int) {
        height = value
    }

    override fun build(): BoardSize = BoardSize(
        width = width,
        height = height,
    )
}
