class MineSweeperBoard(
    val field: List<Field>
) {

    companion object {
        fun of(height: Int, width: Int, mineCount: Int): MineSweeperBoard {
            validate(height = height, width = width, mineCount = mineCount)
            val safeZoneCount = (height * width) - mineCount
            val fields: MutableList<Field> = mutableListOf()
            repeat(safeZoneCount) { fields.add(SafeZone()) }
            repeat(mineCount) { fields.add(Mine()) }
            return MineSweeperBoard(fields.shuffled())
        }

        private fun validate(height: Int, width: Int, mineCount: Int) {
            require(height >= 1) { "높이는 1이상의 값이어야 합니다." }
            require( width >= 1) { "너비는 1이상의 값이어야 합니다." }
            require(mineCount >= 1) { "지뢰찾기 보드에는 지뢰가 1개이상 존재해야합니다." }
        }
    }
}
