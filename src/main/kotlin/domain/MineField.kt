package domain

class MineField(private val mines: List<MutableList<Slot>>) {

    fun isMine(x: Int, y: Int) = mines[x][y].isMine()

    fun isChecked(x: Int, y: Int) = mines[x][y].isChecked
}
