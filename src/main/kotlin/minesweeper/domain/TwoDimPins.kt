package minesweeper.domain

class TwoDimPins private constructor (
    private val values: MutableList<OneDimPins>
) {
    fun getPinsSize(): Int {
        return values.sumOf { it.getPinsSize() }
    }

    fun getPinsAt(height: Int, width: Int): Pin {
        require(height < values.size) { "높이에 $height 는 올바른 위치가 아닙니다" }
        return values[height].getPinAt(width)
    }

    fun changeMine(height: Int, width: Int) {
        require(height < values.size) { "높이에 $height 는 올바른 위치가 아닙니다" }
        values[height].changeMine(width)
        checkSurroundMineNumber(height, width)
    }

    fun closeAllPin() {
        values.forEach { pins ->
            pins.closeAllInRow()
        }
    }

    fun countOpenedPin(): Int {
        return values.sumOf { it.countOpenedPin() }
    }

    fun countMinePin(): Int {
        return values.sumOf { it.countMinePin() }
    }

    fun openPinAt(height: Int, width: Int): Pin {
        require(height < values.size) { "높이에 $height 는 올바른 위치가 아닙니다" }
        return values[height].openPinAt(width)
    }

    private fun checkSurroundMineNumber(height: Int, width: Int) {
        val targetPin = values[height].getPinAt(width)
        val surroundPins = getSurroundMine(height, width)
        surroundPins.forEach { pin ->
            if (pin is NormalPin) {
                pin.comparePinType(targetPin)
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

    private fun getPinsInRow(height: Int): OneDimPins {
        if (height in 0 until values.size) {
            return values[height]
        }
        return OneDimPins(emptyList<Pin>().toMutableList())
    }

    companion object {
        fun of(size: GameBoardSize): TwoDimPins {
            val list = MutableList(size.height) { OneDimPins.of(size.width) }
            return TwoDimPins(list)
        }
    }
}
