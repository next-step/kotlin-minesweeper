package minesweeper.domain

import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.shouldBe

/**
 * @see MapCords
 */
class MapCordsTest : ExpectSpec({

    expect("높이 a와 너비 b를 입력하면 a x b 크기 만큼의 좌표를 반환한다") {
        val actuals = MapCords.of(3, 3)

        val expected = listOf(
            MapCord(0, 0),
            MapCord(0, 1),
            MapCord(0, 2),
            MapCord(1, 0),
            MapCord(1, 1),
            MapCord(1, 2),
            MapCord(2, 0),
            MapCord(2, 1),
            MapCord(2, 2)
        )

        actuals.mapCords.zip(expected) { actual, expect ->
            actual shouldBe expect
        }
    }
})
