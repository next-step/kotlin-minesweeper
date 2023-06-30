package domain

class MineBoard(
    private val rows: List<Row>,
) : List<Row> by rows {
    companion object {
        fun init(height: Int, width: Int): MineBoard {
            return MineBoard(
                List(height) {
                    Row(List(width) { Cell.CLOSED })
                }
            )
        }
    }
}
