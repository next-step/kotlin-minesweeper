package minesweeper.domain

class OneDimPins(private val values: MutableList<Pin>) {
    fun getPinsSize(): Int {
        return values.size
    }

    fun getPinAt(index: Int): Pin {
        require(index <= getPinsSize()) { "너비에 $index 는 올바른 위치가 아닙니다" }
        return values[index]
    }

    fun getPinsBetween(start: Int, end: Int): List<Pin> {
        if (values.size <= 0) return emptyList()
        val startPoint = if (start in 0 until getPinsSize()) start else 0
        val endPoint = if (end in 0 until getPinsSize()) end else getPinsSize() - 1
        require(startPoint <= endPoint) { "범위지정을 확인해주세요" }

        return values.slice(startPoint..endPoint)
    }

    fun closeAllInRow() {
        for ((index, value) in values.withIndex()) {
            values[index] = value.changeToClosePin()
        }
    }

    fun openPinAt(index: Int): Pin {
        require(index <= getPinsSize()) { "너비에 $index 는 올바른 위치가 아닙니다" }
        values[index] = (values[index] as ClosePin).open()
        return getPinAt(index)
    }

    fun changeMine(index: Int) {
        require(index <= getPinsSize()) { "$index 는 올바른 위치가 아닙니다" }
        if (values[index] is NormalPin) {
            val pin = (values[index] as NormalPin).changeToMine()
            values[index] = pin
        }
    }

    companion object {
        fun of(size: Int): OneDimPins {
            val pins = MutableList(size) { NormalPin() as Pin }
            return OneDimPins(pins)
        }
    }
}
