package domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class CoordinateTest : StringSpec({
    /**
     * 1 2 3
     * 8 M 4
     * 7 6 5
     *
     * M 는 내 위치
     * 숫자는 방문할 좌표 테스트 순서
     */
    "왼쪽 위 대각선의 좌표를 방문한다." {
        val coordinate = coordinateOf(2, 2)
        val targetCoordinate = coordinateOf(1, 1)

        val targetDirections = Directions.LEFT_UP_DIAGONAL
        val movedCoordinate = coordinate.movedCoordinate(targetDirections)

        movedCoordinate shouldBe targetCoordinate
    }

    "위쪽 좌표를 방문한다." {
        val coordinate = coordinateOf(2, 2)
        val targetCoordinate = coordinateOf(1, 2)

        val targetDirections = Directions.UP
        val movedCoordinate = coordinate.movedCoordinate(targetDirections)

        movedCoordinate shouldBe targetCoordinate
    }

    "오른쪽 위 대각선 좌표를 방문한다." {
        val coordinate = coordinateOf(2, 2)
        val targetCoordinate = coordinateOf(1, 3)

        val targetDirections = Directions.RIGHT_UP_DIAGONAL
        val movedCoordinate = coordinate.movedCoordinate(targetDirections)

        movedCoordinate shouldBe targetCoordinate
    }

    "오른쪽 좌표를 방문한다." {
        val coordinate = coordinateOf(2, 2)
        val targetCoordinate = coordinateOf(2, 3)

        val targetDirections = Directions.RIGHT
        val movedCoordinate = coordinate.movedCoordinate(targetDirections)

        movedCoordinate shouldBe targetCoordinate
    }

    "오른쪽 대각선 아래 좌표를 방문한다." {
        val coordinate = coordinateOf(2, 2)
        val targetCoordinate = coordinateOf(3, 3)

        val targetDirections = Directions.RIGHT_DOWN_DIAGONAL
        val movedCoordinate = coordinate.movedCoordinate(targetDirections)

        movedCoordinate shouldBe targetCoordinate
    }

    "아래쪽 좌표를 방문한다." {
        val coordinate = coordinateOf(2, 2)
        val targetCoordinate = coordinateOf(3, 2)

        val targetDirections = Directions.DOWN
        val movedCoordinate = coordinate.movedCoordinate(targetDirections)

        movedCoordinate shouldBe targetCoordinate
    }

    "왼쪽 대각선 아래 좌표를 방문한다." {
        val coordinate = coordinateOf(2, 2)
        val targetCoordinate = coordinateOf(3, 1)

        val targetDirections = Directions.LEFT_DOWN_DIAGONAL
        val movedCoordinate = coordinate.movedCoordinate(targetDirections)

        movedCoordinate shouldBe targetCoordinate
    }

    "왼쪽 좌표를 방문한다." {
        val coordinate = coordinateOf(2, 2)
        val targetCoordinate = coordinateOf(2, 1)

        val targetDirections = Directions.LEFT
        val movedCoordinate = coordinate.movedCoordinate(targetDirections)

        movedCoordinate shouldBe targetCoordinate
    }
})
