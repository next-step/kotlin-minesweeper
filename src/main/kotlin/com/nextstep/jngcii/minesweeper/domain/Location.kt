package com.nextstep.jngcii.minesweeper.domain

data class Location(
    val x: Int,
    val y: Int,
) {
    private var _isMine: Boolean = false
    val isMine get() = _isMine

    init {
        require(x >= 0 && y >= 0) { "입력 좌표가 0 이상이어야 합니다." }
    }

    fun pick() {
        if (_isMine) {
            throw IllegalStateException("이미 지뢰로 선택된 위치입니다.")
        }
        _isMine = true
    }
}
