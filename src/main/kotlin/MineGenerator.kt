import java.util.*

abstract class MineGenerator(var mineNumber: Int, var dotNumber: Int) {


    fun generate(): DotState {
        validate()
        dotNumber--
        if (isMine()) {
            mineNumber--
            return MineState()
        }
        return NormalState()
    }

    private fun validate() {
        require(mineNumber >= 0) { "지뢰 개수는 음수일수 없습니다" }
        require(dotNumber >= 0) { "점 개수는 음수일수 없습니다" }
    }

    abstract fun isMine() : Boolean



}
