package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.StringSpec

class CountOfMineTest : StringSpec({
    "지뢰 갯수를 1 미만으로 입력받으면 예외를 발생시킨다." {
        listOf(
            0,
            -1,
        ).forEach {
            // when // then
            shouldThrowExactly<IllegalArgumentException> { CountOfMine(it) }
        }
    }
})
