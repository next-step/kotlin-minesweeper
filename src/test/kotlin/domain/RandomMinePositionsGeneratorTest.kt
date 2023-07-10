package domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec

class RandomMinePositionsGeneratorTest : FunSpec({
    context("지뢰는 0보다 많아야 한다.") {
        shouldThrow<IllegalArgumentException> {
            RandomMinePositionsGenerator(BoardSize(3, 3), -1).generate()
        }
    }

    context("지뢰는 보드의 칸 수보다 적어야 한다.") {
        shouldThrow<IllegalArgumentException> {
            RandomMinePositionsGenerator(BoardSize(3, 3), 9).generate()
        }
    }
})
