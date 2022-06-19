package minesweeper.domain

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class RandomMineCoordinateStrategyTest : DescribeSpec({

    describe("mineCoordinates") {
        it("좌표 목록과 지뢰 개수를 입력 받아 지뢰 개수만큼 랜덤한 좌표를 반환한다") {
            val coordinates = listOf(
                Coordinate(CoordinateIndex(0), CoordinateIndex(0)),
                Coordinate(CoordinateIndex(1), CoordinateIndex(1)),
            )

            val mineCoordinates = RandomMineCoordinateStrategy.mineCoordinates(coordinates, 1)

            mineCoordinates.size shouldBe 1
        }
    }
})
