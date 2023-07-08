package domain

object MinePlanter {

    fun plantMines(row: Row, mineCount: MineCount): Row {
        val spacesToPlantMine = row.spaces.toMutableList()
        minePositions(row, mineCount).forEach {
            spacesToPlantMine[it] = Space.Mine
        }
        return Row(spacesToPlantMine)
    }

    private fun minePositions(spaces: Row, mineCount: MineCount): List<Int> {
        return spaces.spaces
            .indices
            .shuffled()
            .take(mineCount.value)
    }
}
