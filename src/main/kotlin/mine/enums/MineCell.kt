package mine.enums

sealed class MineCell {
    data object MINE : MineCell()

    data class Number(val value: Int) : MineCell()
}
