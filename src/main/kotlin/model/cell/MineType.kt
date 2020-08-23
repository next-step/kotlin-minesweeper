package model.cell

enum class MineType(private val value: Int) {
    MINE(-1),
    ZERO(0);

    override fun toString(): String {
        return if (value < 0) "*" else "C"
    }
}
