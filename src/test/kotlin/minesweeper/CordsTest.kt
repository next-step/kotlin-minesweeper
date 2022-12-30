package minesweeper

import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.shouldBe

/**
 * @see Cords
 */
class CordsTest : ExpectSpec({

    expect("높이와 너비를 입력하면 해당 크기 만큼의 좌표 묶음을 생성한다") {
        val height = 3
        val width = 3

        val cords = Cords.of(height, width)

        cords.mapCords.size shouldBe 9
        cords.mapCords shouldContainAll listOf(
            MapCord(0, 0),
            MapCord(0, 1),
            MapCord(0, 2),
            MapCord(1, 0),
            MapCord(1, 1),
            MapCord(1, 2),
            MapCord(2, 0),
            MapCord(2, 1),
            MapCord(2, 2),
        )
    }

    expect("직사각형의 경우에도 좌표 묶음을 생성할 수 있다.") {
        val height = 4
        val width = 3

        val cords = Cords.of(height, width)

        cords.mapCords.size shouldBe 12
        cords.mapCords shouldContainAll listOf(
            MapCord(0, 0),
            MapCord(0, 1),
            MapCord(0, 2),
            MapCord(0, 3),
            MapCord(1, 0),
            MapCord(1, 1),
            MapCord(1, 2),
            MapCord(1, 3),
            MapCord(2, 0),
            MapCord(2, 1),
            MapCord(2, 2),
            MapCord(2, 3),
        )
    }
})
