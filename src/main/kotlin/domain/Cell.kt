package domain

sealed class Cell {
    data object Empty : Cell()

    data object MineCell : Cell()

    companion object {
        fun create(isMine: Boolean): Cell = if (isMine) MineCell else Empty
    }

    fun display(): String = when (this) {
        is Empty -> Display.EMPTY.toString()
        is MineCell -> Display.MINECELL.toString()
    }
}
