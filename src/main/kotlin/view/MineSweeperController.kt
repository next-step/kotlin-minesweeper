package view

class MineSweeperController(
    private val inputView: MineSweeperInputView,
) {

    fun start() {
        inputView.readInitProperty()
    }
}
