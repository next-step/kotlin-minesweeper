class MineSweeperBoard(
    val field: List<List<Field>>
) {

    companion object {
        fun of(height: Int, width: Int, mineCount: Int): MineSweeperBoard {
            return MineSweeperBoard(List(height) { List(width) { SafeZone() } })
        }
    }
}
