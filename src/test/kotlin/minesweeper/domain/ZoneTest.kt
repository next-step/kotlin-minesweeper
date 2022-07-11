package minesweeper.domain

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.StringSpec

class ZoneTest : StringSpec({
    "해당 칸을 오픈한다." {
        // given
        listOf(
            MineZone(),
            SafeZone(),
        ).forEach {
            // when // then
            shouldNotThrowAny { it.open() }
        }
    }

    "이미 open된 칸을 오픈하면 예외를 발생시킨다." {
        // given
        listOf(
            MineZone(false),
            SafeZone(false),
        ).forEach {
            // when // then
            shouldThrowExactly<IllegalArgumentException> { it.open() }
        }
    }
})
