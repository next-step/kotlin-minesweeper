package minesweeper.domain

class Pins private constructor (
    private val values: MutableList<PinsInRow>
) {
    fun getPinsSize(): Int {
        return values.sumOf { it.getPinsSize() }
    }

    fun getPinsAt(height: Int, width: Int): Pin {
        checkHeight(height)
        return values[height].getPinAt(width)
    }

    fun changeMine(height: Int, width: Int) {
        checkHeight(height)
        values[height].changeMine(width)
        checkSurroundMineNumber(height, width)
    }

    fun countOpenedPin(): Int {
        return values.sumOf { it.countOpenedPin() }
    }

    fun countMinePin(): Int {
        return values.sumOf { it.countMinePin() }
    }

    fun openPinAt(height: Int, width: Int): Pin {
        checkHeight(height)
        return values[height].openPinAt(width)
    }

    private fun checkHeight(height: Int) {
        require(height in 0 until values.size) { "높이에 $height 는 올바른 위치가 아닙니다" }
    }

    private fun checkSurroundMineNumber(height: Int, width: Int) {
        val targetPin = values[height].getPinAt(width)
        val surroundPins = getSurroundMine(height, width)
        surroundPins.forEach { pin ->
            if (!pin.isMinePin()) {
                (pin as NormalPin).comparePinType(targetPin)
            }
        }
    }

    private fun getSurroundMine(height: Int, width: Int): List<Pin> {
        return (
            getPinsInRow(height - 1).getPinsBetween(width - 1, width + 1) +
                getPinsInRow(height).getPinsBetween(width - 1, width + 1) +
                getPinsInRow(height + 1).getPinsBetween(width - 1, width + 1)
            )
    }

    private fun getPinsInRow(height: Int): PinsInRow {
        if (height in 0 until values.size) {
            return values[height]
        }
        return PinsInRow(emptyList<Pin>().toMutableList())
    }

    companion object {
        fun of(size: GameBoardSize): Pins {
            val list = MutableList(size.height) { PinsInRow.of(size.width) }
            return Pins(list)
        }
    }
}
