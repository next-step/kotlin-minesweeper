package com.nextstep.jngcii.minesweeper.domain

fun interface PickStrategy {
    fun pick(target: Locations, count: Int)
}

object RandomPickStrategy : PickStrategy {
    override fun pick(target: Locations, count: Int) {
        val pairs = target.locations

        check(pairs.size >= count) {
            "${pairs.size}개 중 ${count}개를 고를 수 없습니다."
        }

        pairs.shuffled()
            .take(count)
            .forEach(Location::pick)
    }
}
