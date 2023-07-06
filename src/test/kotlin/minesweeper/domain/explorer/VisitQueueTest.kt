package minesweeper.domain.explorer

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import minesweeper.domain.position.Position
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class VisitQueueTest {

    private lateinit var queue: VisitQueue

    @BeforeEach
    fun setup() {
        queue = VisitQueue()
    }

    @Test
    fun `비어있는 큐에 pop()을 하면 IllegalArgumentException 발생`() {
        shouldThrow<IllegalArgumentException> {
            queue.pop()
        }
    }

    @Test
    fun `큐에 요소가 있으면 isEmpty 는 false 리턴`() {
        queue.push(Position(x = 1, y = 1))

        queue.isEmpty() shouldBe false
    }

    @Test
    fun `큐에 요소가 없으면 isEmpty 는 true 리턴`() {
        queue.isEmpty() shouldBe true
    }
}
