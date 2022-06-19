package domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import vo.Point

internal class RowTest : StringSpec({

    fun coordinate(x: Int, y: Int) = Coordinate(Point(x), Point(y))

    "빈 리스트로는 인스턴스 생성할 수 없다" {
        shouldThrow<IllegalArgumentException> {
            Row(emptyList())
        }
    }

    "동일한 좌표를 가진 요소가 있으면 인스턴스 생성할 수 없다" {
        shouldThrow<IllegalArgumentException> {
            val cells = listOf(
                Mine(coordinate(1, 1)),
                Empty(coordinate(1, 1)),
            )
            Row(cells)
        }
    }

    "y 좌표가 모두 동일하지 않으면 인스턴스 생성할 수 없다" {
        shouldThrow<IllegalArgumentException> {
            val cells = listOf(
                Mine(coordinate(1, 1)),
                Empty(coordinate(2, 2)),
            )
            Row(cells)
        }
    }

    "주어진 cell 을 가지는 인스턴스가 생성된다" {
        val cells = listOf(
            Mine(coordinate(1, 2)),
            Empty(coordinate(2, 2)),
        )

        val row = Row(cells)
        row.size shouldBe 2
    }

    "열 기준으로 정렬된 row 를 반환한다" {
        val cells = listOf(
            Mine(coordinate(3, 1)),
            Mine(coordinate(1, 1)),
            Mine(coordinate(4, 1)),
            Mine(coordinate(2, 1)),
        )

        val row = Row(cells)

        row.ordered.cells.map { it.coordinate.x } shouldBe listOf(1, 2, 3, 4).map(::Point)
    }
})
