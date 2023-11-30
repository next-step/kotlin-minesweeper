package domain

class MinesweeperGame(
    val fieldInfo: FieldInfo,
    val mineSize: Int
) {
    val field: Array<Array<SlotInfo>>
        get() = field.copyOf()

    init {
        val isMineExists: Array<Boolean> = Array(fieldInfo.width.value * fieldInfo.height.value) { it < mineSize }.apply {
            shuffle()
        }
        field = Array(fieldInfo.width.value) { low: Int ->
            Array(fieldInfo.height.value) { coulmn: Int ->
                SlotInfo(
                    isMineExist = isMineExists[(fieldInfo.width.value * low) + coulmn],
                    mark = Mark.NONE
                )
            }
        }
    }
}
