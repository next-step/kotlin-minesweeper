package com.nextstep.jngcii.minesweeper.domain

data class RowElement(val isMine: Boolean) {
    var risk: Int = 0
        private set

    fun increaseRisk() {
        check(!isMine) {
            "지뢰는 risk를 증가시킬 수 없습니다."
        }
        risk++
    }

    companion object {
        fun from(booleans: List<Boolean>) = booleans.map { RowElement(it) }
    }
}
