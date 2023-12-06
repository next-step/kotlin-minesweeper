package minesweeper.domain

sealed class Cell {
    var isOpened: Boolean = false
        protected set

    abstract fun open()
}

class MineCell : Cell() {
    override fun open() {
        throw IllegalAccessException("지뢰는 열 수 없습니다.")
    }
}

class EmptyCell : Cell() {
    override fun open() {
        super.isOpened = true
    }
}
