package v2minesweeper.domain

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec

class ZoneTest : FunSpec({
    test("지뢰영역을 open하면 예외를 발생시킨다.") {
        // given
        val mineZone = MineZone()

        // when // then
        shouldThrowExactly<IllegalArgumentException> { mineZone.open() }
    }
})
