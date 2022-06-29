package com.nextstep.jngcii.minesweeper.domain

fun interface OrderStrategy {
    fun pick(): (List<Location>) -> Unit
}

object ShuffleOrderStrategy : OrderStrategy {
    override fun pick(): (List<Location>) -> Unit = { it.shuffled() }
}
