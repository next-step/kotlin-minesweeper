package com.nextstep.jngcii.minesweeper.domain

data class Location(
    val x: Int,
    val y: Int,
) {
    var isMine: Boolean = false
        private set

    var risk: Int = 0
        private set

    val aroundPairs
        get() = arounds.map { (aroundX, aroundY) -> x + aroundX to y + aroundY }

    init {
        require(x >= 0 && y >= 0) { "입력 좌표가 0 이상이어야 합니다." }
    }

    fun pick() {
        check(!isMine) { "이미 지뢰로 선택된 위치입니다." }
        isMine = true
    }

    fun increaseRisk() {
        check(!isMine) { "지뢰에는 위험도를 증가시킬 수 없습니다." }
        risk += 1
    }

    companion object {
        private val limits = (-1..1).toList()
        private val arounds = limits
            .flatMap { x -> limits.map { y -> x to y } }
            .filter { !(it.first == 0 && it.second == 0) }
    }
}
