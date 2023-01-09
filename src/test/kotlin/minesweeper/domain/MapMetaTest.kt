package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.throwable.shouldHaveMessage

class MapMetaTest : StringSpec({
    "맵의 높이는 0 혹은 음수일 경우 예외가 발생한다." {
        forAll(
            row(0),
            row(-1)
        ) { height ->
            val message = shouldThrow<IllegalArgumentException> {
                MapMeta(
                    height = height,
                    width = 10,
                    mineCount = 10
                )
            }

            message shouldHaveMessage "높이는 최소 1 이상이여야 합니다."
        }
    }

    "맵의 너비는 0 혹은 음수일 경우 예외가 발생한다." {
        forAll(
            row(0),
            row(-1)
        ) { width ->
            val message = shouldThrow<IllegalArgumentException> {
                MapMeta(
                    height = 10,
                    width = width,
                    mineCount = 10
                )
            }

            message shouldHaveMessage "너비는 최소 1 이상이여야 합니다."
        }
    }

    "맵의 지뢰 갯수가 0 혹은 음수일 경우 예외가 발생한다." {
        forAll(
            row(0),
            row(-1)
        ) { mineCount ->
            val message = shouldThrow<IllegalArgumentException> {
                MapMeta(
                    height = 10,
                    width = 10,
                    mineCount = mineCount
                )
            }

            message shouldHaveMessage "지뢰 갯수는 최소 1 이상이여야 합니다."
        }
    }

    "맵의 지뢰 갯수가 맵의 Cell 수 보다 많을 경우 예외가 발생한다." {
        forAll(
            row(101),
            row(120)
        ) { mineCount ->
            val message = shouldThrow<IllegalArgumentException> {
                MapMeta(
                    height = 10,
                    width = 10,
                    mineCount = mineCount
                )
            }

            message shouldHaveMessage "지뢰 갯수는 100 이하 합니다."
        }
    }
})
