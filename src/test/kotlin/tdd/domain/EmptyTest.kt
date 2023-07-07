package tdd.domain

import io.kotest.matchers.should
import io.kotest.matchers.types.instanceOf
import org.junit.jupiter.api.Test

class EmptyTest {

    @Test
    fun `Empty is Closed State`() {
        Empty should instanceOf<Closed>()
    }
}
