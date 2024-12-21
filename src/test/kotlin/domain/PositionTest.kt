package domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class PositionTest : BehaviorSpec({
    given("중심(2, 2) 좌표가 주어졌을 때") {
        val position = Position(2, 2)

        `when`("adjacentPositions()를 호출하면") {
            val adjacentPositions = position.adjacentPositions()

            then("주변 8칸의 좌표가 반환되어야 한다") {
                // (1,1), (1,2), (1,3), (2,1), (2,3), (3,1), (3,2), (3,3)
                adjacentPositions.size shouldBe 8
                adjacentPositions shouldBe
                    listOf(
                        Position(1, 1), Position(1, 2), Position(1, 3),
                        Position(2, 1), Position(2, 3),
                        Position(3, 1), Position(3, 2), Position(3, 3),
                    )
            }
        }
    }

    given("모서리 (1,1) 좌표가 주어졌을 때") {
        val position = Position(1, 1)

        `when`("adjacentPositions()를 호출하면") {
            val adjacentPositions = position.adjacentPositions()

            then("상단 왼쪽 모서리이므로, 필터링 후 3개의 좌표만 반환된다") {
                // (1,2), (2,1), (2,2) 3개
                adjacentPositions.size shouldBe 3
                adjacentPositions shouldBe
                    listOf(
                        Position(1, 2),
                        Position(2, 1),
                        Position(2, 2),
                    )
            }
        }
    }

    given("가장자리이지만 모서리가 아닌 (1, 2) 좌표가 주어졌을 때") {
        val position = Position(1, 2)

        `when`("adjacentPositions()를 호출하면") {
            val adjacentPositions = position.adjacentPositions()

            then("위가 막혀 있고 좌우 아래 방향은 열려있으므로 필터링 후 5개의 좌표만 반환된다") {
                // (1,1), (1,3), (2,1), (2,2), (2,3) 총 5개
                adjacentPositions.size shouldBe 5
                adjacentPositions shouldBe
                    listOf(
                        Position(1, 1),
                        Position(1, 3),
                        Position(2, 1),
                        Position(2, 2),
                        Position(2, 3),
                    )
            }
        }
    }
})
