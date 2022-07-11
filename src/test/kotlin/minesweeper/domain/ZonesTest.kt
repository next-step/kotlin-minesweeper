package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class ZonesTest : StringSpec({
    "지뢰찾기 판에 존재하지 않는 칸을 open하면 예외를 발생시킨다." {
        // given
        val zones = Zones(
            mapOf(
                Position(1, 1) to MineZone(true),
                Position(1, 2) to SafeZone(true),
                Position(2, 1) to SafeZone(true),
                Position(2, 2) to SafeZone(true),
            ),
        )

        // when // then
        shouldThrowExactly<IllegalArgumentException> { zones.openAt(Position(3, 1)) }
    }

    "open되지 않은 안전한 칸이 있는지 확인한다." {
        listOf(
            row(
                mapOf(
                    Position(1, 1) to SafeZone(true),
                    Position(1, 2) to SafeZone(true),
                    Position(2, 1) to SafeZone(true),
                    Position(2, 2) to SafeZone(true),
                ).let { Zones(it) },
                true,
            ),
            row(
                mapOf(
                    Position(1, 1) to SafeZone(false),
                    Position(1, 2) to SafeZone(false),
                    Position(2, 1) to SafeZone(false),
                    Position(2, 2) to SafeZone(true),
                ).let { Zones(it) },
                true,
            ),
            row(
                mapOf(
                    Position(1, 1) to SafeZone(false),
                    Position(1, 2) to SafeZone(false),
                    Position(2, 1) to SafeZone(false),
                    Position(2, 2) to SafeZone(false),
                ).let { Zones(it) },
                false,
            ),
        ).forEach { (zones, expected) ->
            // when
            val actual = zones.isAnyHiddenSafeZone()

            // then
            actual shouldBe expected
        }
    }

    "모든 지뢰칸이 hidden 상태인지 확인한다." {
        listOf(
            row(
                mapOf(
                    Position(1, 1) to MineZone(true),
                    Position(1, 2) to MineZone(true),
                    Position(2, 1) to MineZone(true),
                    Position(2, 2) to MineZone(true),
                ).let { Zones(it) },
                true,
            ),
            row(
                mapOf(
                    Position(1, 1) to MineZone(false),
                    Position(1, 2) to MineZone(true),
                    Position(2, 1) to MineZone(true),
                    Position(2, 2) to MineZone(true),
                ).let { Zones(it) },
                false,
            ),
        ).forEach { (zones, expected) ->
            // when
            val actual = zones.isAllHiddenMineZone()

            // then
            actual shouldBe expected
        }
    }
})
