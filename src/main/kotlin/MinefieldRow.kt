data class MinefieldRow(val mineFields: List<Int>) {
    operator fun get(index: Int): Int = mineFields[index]

    fun markAsMine(targetIndex: Int): MinefieldRow {
        val newMineFields = mineFields.toMutableList()
        newMineFields[targetIndex] = Const.MINE_VALUE
        return MinefieldRow(newMineFields)
    }

    fun incrementMineCountsIfAdjacent(newWidth: Int): MinefieldRow {
        val newMineFields = mineFields.toMutableList()
        newMineFields[newWidth] += INCREASE_VALUE
        return MinefieldRow(newMineFields)
    }

    fun isMine(newWidth: Int): Boolean {
        return mineFields[newWidth] == Const.MINE_VALUE
    }

    companion object {
        private const val INCREASE_VALUE = 1
        private const val DEFAULT_VALUE = 0
        fun of(width: Int): MinefieldRow = MinefieldRow((Const.START_INDEX until width).map { DEFAULT_VALUE })
    }
}
