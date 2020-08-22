package model.cell

const val MINE = "MINE"
const val NOT_MINE = "NOT_MINE"

data class MineStatus(val isMine: Boolean) {
    override fun toString(): String {
        if (isMine) return "*"
        return "C"
    }
}
