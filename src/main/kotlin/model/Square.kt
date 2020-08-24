package model

class Square(private val width: Number, private val height: Number) {
    fun make(): MinePlate {
        val rows = arrayListOf<MineRow>()
        repeat(height.value) {
            val cols = MineColumn(width)
            rows.add(MineRow(cols))
        }
        return MinePlate(rows)
    }
}
