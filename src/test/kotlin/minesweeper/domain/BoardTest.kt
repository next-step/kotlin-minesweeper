package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import minesweeper.domain.point.Mines

class BoardTest : StringSpec({

    "보드의 높이, 너비는 양수 값이어야 합니다." {
        forAll(
            row(0, 0),
            row(0, 1),
            row(1, 0),
            row(-1, 1),
            row(1, -1),
            row(-1, -1),
        ) { height, width ->
            shouldThrow<IllegalArgumentException> {
                Board(
                    Height(height),
                    Width(width),
                    Mines(Height(height), Width(width), MineCount(0), DefaultMineGenerator()),
                )
            }
        }
    }

    "보드의 높이 * 너비를 가진 정수 배열을 생성한다." {
        forAll(
            row(1, 1),
            row(5, 30),
            row(10, 10),
        ) { height, width ->
            val board =
                Board(
                    Height(height),
                    Width(width),
                    Mines(Height(height), Width(width), MineCount(0), DefaultMineGenerator()),
                )
            board.points.size shouldBe height
            board.points[0].size shouldBe width
        }
    }
})
