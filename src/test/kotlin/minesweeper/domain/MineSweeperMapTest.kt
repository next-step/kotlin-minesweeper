package minesweeper.domain

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Assertions.*

class MineSweeperMapTest: ShouldSpec({
    context("지도 생성 시 지뢰 숫자는") {
        should("지도 크기 보다 작을 경우 생성에 성공 한다.") {
            shouldNotThrow<RuntimeException> { MineSweeperMap.of(height = 5, width = 5, mines = 24) }
        }
        should("지도 크기와 같을 경우 생성에 성공 한다.") {
            shouldNotThrow<RuntimeException> { MineSweeperMap.of(height = 5, width = 5, mines = 25) }
        }
        should("지도 크기 보다 클 경우 생성에 실패 한다.") {
            shouldThrow<RuntimeException> { MineSweeperMap.of(height = 5, width = 5, mines = 26) }
        }
    }

    should("지도에 존재 하는 지뢰의 숫자는 생성 시 입력한 지뢰 숫자와 같다") {
        val mineSweeperMap = MineSweeperMap.of(height = 5, width = 5, mines = 10)
        val actual = mineSweeperMap.lines.sumOf { it.points.filterIsInstance<MinePoint>().size }
        actual shouldBe 10
    }
})
