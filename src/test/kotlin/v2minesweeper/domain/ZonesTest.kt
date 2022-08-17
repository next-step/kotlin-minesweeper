package v2minesweeper.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class ZonesTest : FunSpec({
    test("지뢰찾기판의 모든 위치의 인근 지뢰갯수를 찾는다.") {
        // given
        val zones = mapOf(
            (1 to 1) to SafeZone(),
            (1 to 2) to MineZone(),
            (1 to 3) to MineZone(),
            (2 to 1) to MineZone(),
            (2 to 2) to SafeZone(),
            (2 to 3) to SafeZone(),
            (3 to 1) to SafeZone(),
            (3 to 2) to SafeZone(),
            (3 to 3) to SafeZone()
        ).toZones()

        // when
        val result = zones.findAllNearMineNumber()

        // then
        result.size shouldBe 9
        result[1 to 1] shouldBe 2
        result[1 to 2] shouldBe 2
        result[1 to 3] shouldBe 1
        result[2 to 1] shouldBe 1
        result[2 to 2] shouldBe 3
        result[2 to 3] shouldBe 2
        result[3 to 1] shouldBe 1
        result[3 to 2] shouldBe 1
        result[3 to 3] shouldBe 0
    }

    test("지뢰 영역을 오픈하지 않았는지 확인한다.") {
        listOf(
            row(
                mapOf(
                    Position(1 to 1) to MineZone(true),
                    Position(1 to 2) to SafeZone(true),
                    Position(2 to 1) to SafeZone(true),
                    Position(2 to 2) to SafeZone(true)
                ),
                true
            ),
            row(
                mapOf(
                    Position(1 to 1) to MineZone(false),
                    Position(1 to 2) to SafeZone(true),
                    Position(2 to 1) to SafeZone(true),
                    Position(2 to 2) to SafeZone(true)
                ),
                false
            )
        ).forEach { (zoneGroup, expected) ->
            // given
            val zones = Zones(zoneGroup)

            // when
            val actual = zones.isNotOpenMineZone()

            // then
            actual shouldBe expected
        }
    }

    test("아직 오픈하지 않은 안전 영역이 있는지 여부를 확인한다.") {
        listOf(
            row(
                mapOf(
                    Position(1 to 1) to MineZone(true),
                    Position(1 to 2) to SafeZone(true),
                    Position(2 to 1) to SafeZone(true),
                    Position(2 to 2) to SafeZone(true)
                ),
                true
            ),
            row(
                mapOf(
                    Position(1 to 1) to MineZone(true),
                    Position(1 to 2) to SafeZone(false),
                    Position(2 to 1) to SafeZone(false),
                    Position(2 to 2) to SafeZone(false)
                ),
                false
            )
        ).forEach { (zoneGroup, expected) ->
            // given
            val zones = Zones(zoneGroup)

            // when
            val actual = zones.existHiddenSafeZone()

            // then
            actual shouldBe expected
        }
    }
})
