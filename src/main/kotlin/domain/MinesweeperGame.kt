package domain

class MinesweeperGame(
    val width: Int,
    val height: Int,
    val mineSize: Int
) {
    val field: Array<Array<SlotInfo>>
        get() = field.copyOf()

    init {
        val isMineExists: Array<Boolean> = Array(width * height) { it < mineSize }.apply {
            shuffle()
        }
        field = Array(width) { low: Int ->
            Array(height) { coulmn: Int ->
                SlotInfo(
                    isMineExist = isMineExists[(width * low) + coulmn],
                    mark = Mark.NONE
                )
            }
        }
    }
}
