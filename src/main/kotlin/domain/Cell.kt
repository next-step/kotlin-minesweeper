package domain

sealed class Cell {
    object Empty : Cell()
    object MineCell : Cell()

    companion object {
        fun create(isMine: Boolean): Cell = if (isMine) MineCell else Empty
    }

    fun display(): String = when (this) {
        is Empty -> Display.EMPTY.toString()
        is MineCell -> Display.MINECELL.toString()
    }
}
