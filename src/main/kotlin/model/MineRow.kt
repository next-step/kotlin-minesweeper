package model

class MineRow(val column: MineColumn) {
    fun blockCount(): Int {
        return column.blocks.count { it.isBlock() }
    }

    fun mineCount(): Int {
        return column.blocks.count { it.isMine() }
    }
}
