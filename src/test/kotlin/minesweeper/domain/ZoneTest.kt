package minesweeper.domain

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.row
import io.kotest.matchers.shouldBe

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

    "open할 수 있는 칸인지 확인한다." {
        // given
        listOf(
            row(MineZone(true), false),
            row(MineZone(true), false),
            row(SafeZone(true), true),
            row(SafeZone(false), false),
        ).forEach { (zone, expected) ->
            // when
            val actual = zone.isOpenable()

            // then
            actual shouldBe expected
        }
    }
})
