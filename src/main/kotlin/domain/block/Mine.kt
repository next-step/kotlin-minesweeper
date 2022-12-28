package domain.block

import java.lang.IllegalStateException

class Mine : Block() {
    override fun availableOpen(): Boolean = false
    override fun isMine(): Boolean = true

    override fun openBlock() {
        throw IllegalStateException("지뢰는 열 수 없어요")
    }
}
