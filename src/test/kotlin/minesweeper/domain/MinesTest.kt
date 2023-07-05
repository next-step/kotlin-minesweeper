package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class MinesTest : StringSpec({

    "Mines는 전달받은 size의 수만큼 mine을 생성한다." {
        val mines = Mines.generateMine(3, Size(2, 4))
        mines.list.size shouldBe 3
    }

    "mine의 수가 cell보다 크면 예외가 발생한다." {
        shouldThrow<IllegalArgumentException> {
            Mines.generateMine(10, Size(2, 4))
        }.message shouldBe "mine의 수는 cell의 수보다 작거나 같아야 됩니다."
    }
})
