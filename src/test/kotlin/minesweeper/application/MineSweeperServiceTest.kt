package minesweeper.application

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import minesweeper.domain.SymbolType
import minesweeper.request.MineBoardCreateRequest
import minesweeper.strategy.DefaultMineBoardCreateStrategy

class MineSweeperServiceTest : FunSpec({
    val service = MineSweeperService(mineBoardCreateStrategy = DefaultMineBoardCreateStrategy())

    test("유효한 요청 정보를 전달하면 지뢰찾기 보드를 생성해 반환한다.") {
        val width = 5
        val height = 5
        val mineCapacity = 5
        val actual = service.createMineBoard(
            request = MineBoardCreateRequest(
                height = height, width = width, mineCapacity = mineCapacity
            )
        )

        actual.height shouldBe height
        actual.width shouldBe width

        actual.lines shouldHaveSize height
        actual.lines.forEach {
            it shouldHaveSize width
        }
        actual.lines.sumOf {
            it.count { point -> point.symbol == SymbolType.MINE }
        } shouldBe mineCapacity
    }
})
