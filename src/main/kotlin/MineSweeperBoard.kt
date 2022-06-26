class MineSweeperBoard(
    val field: List<Field>
) {

    companion object {
        fun of(height: Int, width: Int, mineCount: Int): MineSweeperBoard {
            val safeZoneCount = (height * width) - mineCount
            val fields: MutableList<Field> = mutableListOf()
            repeat(safeZoneCount) { fields.add(SafeZone()) }
            repeat(mineCount) { fields.add(Mine()) }
            return MineSweeperBoard(fields.shuffled())
        }
    }
}
