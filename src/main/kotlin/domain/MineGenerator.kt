package domain

abstract class MineGenerator(var mineNumber: Int, var dotNumber: Int) {

    fun generate(): DotState {
        require(dotNumber >= 0) { "점 개수는 음수일수 없습니다" }
        dotNumber--
        if ((mineNumber > 0) && isMine()) {
            mineNumber--
            return MineState()
        }
        return NormalState()
    }
    abstract fun isMine(): Boolean
}
