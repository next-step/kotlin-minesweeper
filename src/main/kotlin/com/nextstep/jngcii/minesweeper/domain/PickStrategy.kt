package com.nextstep.jngcii.minesweeper.domain

fun interface PickStrategy {
    fun take(target: List<Int>, count: Int): List<Int>
}

object RandomPickStrategy : PickStrategy {
    override fun take(target: List<Int>, count: Int): List<Int> {
        if (target.size < count) {
            throw IllegalStateException("${target.size}개 중 ${count}개를 고를 수 없습니다.")
        }

        return target.shuffled().take(count)
    }
}
