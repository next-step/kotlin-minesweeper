package minesweeper.domain

class ClosePin(
    private val pin: Pin
) : Pin() {

    init {
        require(pin !is ClosePin) { "ClosePin 을 2 중으로 감쌀 수는 없습니다" }
    }

    fun isMinePin(): Boolean {
        return pin is MinePin
    }

    fun open(): Pin {
        return pin
    }
}
