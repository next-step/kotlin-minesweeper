package minesweeper.domain

class Pins(private val values: MutableList<Pin>) {
    fun getPinsSize(): Int {
        return values.size
    }

    fun getPinAt(height: Int, width: Int): Pin {
        val index = (height + 1) * width
        require(index <= getPinsSize()) { "높이 : $height, 너비: $width 는 올바른 위치가 아닙니다" }
        return values[index]
    }

    fun changeMine(height: Int, width: Int) {
        val index = (height + 1) * width

        require(index <= getPinsSize()) { "높이 : $height, 너비: $width 는 올바른 위치가 아닙니다" }

        values[index] = MinePin()
    }

    companion object {
        fun of(size: GameBoardSize): Pins {
            val totalSize = size.height * size.width
            val values = mutableListOf<Pin>()
            repeat(totalSize) {
                values.add(NormalPin())
            }
            return Pins(values)
        }
    }
}
