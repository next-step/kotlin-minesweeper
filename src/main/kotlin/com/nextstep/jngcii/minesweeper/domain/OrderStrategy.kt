package com.nextstep.jngcii.minesweeper.domain

fun interface OrderStrategy {
    fun pick(total: Int, count: Int): List<Int>
}

object ShuffleOrderStrategy : OrderStrategy {
    override fun pick(total: Int, count: Int) =
        List(total) { it }
            .shuffled()
            .take(count)
}
