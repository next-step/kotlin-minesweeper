package model

class Square(private val width: Number, private val height: Number) {
    fun make(): MinePlate {
        val rows = arrayListOf<ArrayList<Block>>()
        repeat(height.value) {
            val cols = arrayListOf<Block>()
            repeat(width.value) { cols.add(Block()) }
            rows.add(cols)
        }
        return MinePlate(rows)
    }
}
