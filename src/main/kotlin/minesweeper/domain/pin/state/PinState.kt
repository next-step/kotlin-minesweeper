package minesweeper.domain.pin.state

enum class PinState {
    OPEN,
    CLOSE;

    fun toOpen(): PinState {
        check(this == CLOSE) { "닫혀있는 핀만 열 수 있습니다" }
        return OPEN
    }
}
