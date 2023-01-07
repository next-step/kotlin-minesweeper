package minesweeper.domain.component

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.nulls.shouldNotBeNull
import minesweeper.common.value.CoordinateValue
import minesweeper.domain.NearMineCount
import minesweeper.domain.Position

class DefaultComponentTest : FreeSpec({
    "위치값과 지뢰 여부로 컴포넌트를 만들 수 있다" {
        val position = Position(x = CoordinateValue(10), y = CoordinateValue(10))
        val isMine = true
        val nearMineCount = NearMineCount(8)

        val component = DefaultComponent(position = position, isMine = isMine, nearMineCount = nearMineCount)
        component.isMine.shouldBeTrue()
        component.position.shouldNotBeNull()
    }
})
