package com.nextstep.jngcii.minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MineBoardFactoryTest {
    private val notingOrderStrategy = OrderStrategy { total, count ->
        List(total) { it }.take(count)
    }
    private val mineBoardFactory = MineBoardFactory(notingOrderStrategy)

    @Test
    fun `기본지뢰매니저를 사용해 지뢰맵을 만드는 결과 확인해보기`() {
        val meta = MineBoardMeta(3, 5)

        val mineCount = 10

        val mineBoard = mineBoardFactory.create(meta, mineCount)

        assertThat(mineBoard.locations).isEqualTo(
            listOf(
                Location(x = 0, y = 0),
                Location(x = 0, y = 1),
                Location(x = 0, y = 2),
                Location(x = 1, y = 0),
                Location(x = 1, y = 1),
                Location(x = 1, y = 2),
                Location(x = 2, y = 0),
                Location(x = 2, y = 1),
                Location(x = 2, y = 2),
                Location(x = 3, y = 0),
                Location(x = 3, y = 1),
                Location(x = 3, y = 2),
                Location(x = 4, y = 0),
                Location(x = 4, y = 1),
                Location(x = 4, y = 2)
            )
        )
    }
}
