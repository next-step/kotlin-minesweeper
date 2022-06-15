package domain

class MineField {
    data class FieldInfo(val height: Int, val width: Int, val mineCount: Int)
    data class MinePosition(val mineRow: Int, val mineColumn: Int)

    fun buildField(fieldInfo: FieldInfo): List<List<String>> {
        val mineField = List(fieldInfo.height) { MutableList(fieldInfo.width) { EMPTY } }
        val minePositions: MutableList<MinePosition> = mutableListOf()

        while (minePositions.size != fieldInfo.mineCount) {
            val mineRow = (1..fieldInfo.height).random()
            val mineColumn = (1..fieldInfo.width).random()
            val newMinePosition = MinePosition(mineRow, mineColumn)
            if (newMinePosition !in minePositions) {
                minePositions.add(newMinePosition)
            }
        }

        mineField.forEachIndexed { i, row ->
            row.forEachIndexed { j, column ->
                if (MinePosition(i + 1, j + 1) in minePositions) {
                    mineField[i][j] = MINE
                }
            }
            row.toList()
        }

        return mineField
    }
    
    companion object {
        const val EMPTY = "C"
        const val MINE = "*"
    }
}
