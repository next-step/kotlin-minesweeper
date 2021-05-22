package domain.position

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.lang.IllegalArgumentException

internal class DefaultRandomPositionIdFactoryTest {

    @Test
    fun `random으로 position id가 겹치지 않게 생성한다`() {
        val defaultRandomPositionIdFactory = RandomPositionIdFactory()
        val positionIds = defaultRandomPositionIdFactory.positionIds(100, 99)
        assertThat(positionIds).hasSize(100)
        assertThat(positionIds).containsExactlyInAnyOrderElementsOf((0..99).map { PositionId(it) })
        assertThat(positionIds.toSet()).hasSize(100)
    }

    @Test
    fun `만들 수 있는 값보다 더 많은 position을 만들려고 하면 예외를 발생시킨다`() {
        val defaultRandomPositionIdFactory = RandomPositionIdFactory()
        Assertions.assertThatThrownBy { defaultRandomPositionIdFactory.positionIds(101, 99) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }
}
