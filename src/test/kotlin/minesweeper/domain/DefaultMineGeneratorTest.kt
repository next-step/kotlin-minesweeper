package minesweeper.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DefaultMineGeneratorTest : StringSpec({
    "generate()는 입력받은 좌표 범위 내에서 개수 만큼 지뢰를 생성한다." {
        val mines = DefaultMineGenerator().generate(Height(3), Width(3), MineCount(2))
        mines.size shouldBe 2
    }
})
