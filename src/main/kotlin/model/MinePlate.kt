package model

class MinePlate(val value: List<MineRow>) {
    fun block(x: Int, y: Int): Block {
        return value[y].column.blocks[x]
    }

    fun setMineValue(x: Int, y:Int, mineCount: Int) {
        block(x, y).mineCount = mineCount
    }

    fun rowSize(): Int {
        return value.size
    }

    fun colSize(): Int {
        return value[0].column.blocks.size
    }

    fun rowIndices(): IntRange {
        return value.indices
    }

    fun colIndices(row: Int): IntRange {
        return value[row].column.blocks.indices
    }
}
