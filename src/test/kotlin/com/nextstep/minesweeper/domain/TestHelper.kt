package com.nextstep.minesweeper.domain

class TestHelper {
    companion object {
        fun shuffled(): (Iterable<Int>) -> List<Int> = { it -> it.shuffled() }
    }
}
