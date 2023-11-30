package domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec

class MineMapInfoTest : StringSpec({
    "높이가 0 이하면 예외가 발생한다" {
        shouldThrow<IllegalArgumentException> {
            MineMapInfo(Point(0, 10), 4)
        }
    }

    "너비가 0 이하면 예외가 발생한다" {
        shouldThrow<IllegalArgumentException> {
            MineMapInfo(Point(10, 0), 4)
        }
    }

    "지뢰 개수가 0 미만이면 예외가 발생한다" {
        shouldThrow<IllegalArgumentException> {
            MineMapInfo(Point(10, 10), -1)
        }
    }

    "지뢰 개수가 높이와 너비의 곱보다 크거나 같으면 예외가 발생한다" {
        shouldThrow<IllegalArgumentException> {
            MineMapInfo(Point(10, 10), 100)
        }
    }
})
