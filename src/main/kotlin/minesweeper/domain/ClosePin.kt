package minesweeper.domain

class ClosePin(
    private val pin: Pin
) : Pin() {

    init {
        require(pin !is ClosePin) { "ClosePin 을 2 중으로 감쌀 수는 없습니다" }
    }

    fun open(): Pin {
        return pin
    }

    override fun isMinePin(): Boolean {
        return this.pin is MinePin
    }
}
