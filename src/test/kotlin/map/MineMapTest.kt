package map

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldHaveSize
import map.position.Position
import model.Height
import model.MineCount
import model.Width

internal class MineMapTest : StringSpec({
    "주어진 갯수만큼 지뢰를 설정한다" {
        val sut = MineMap(
            MineMap.Property(
                Height(10),
                Width(10),
                MineCount(1),
            )
        ) { Position(0, 0) }

        val snapshot = sut.getMapSnapShot()
        snapshot shouldHaveSize 10
        snapshot.forEach { it.cols shouldHaveSize 10 }
    }
})
