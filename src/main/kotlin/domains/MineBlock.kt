package domains

import views.Output

class MineBlock(override val position: Position) : Block(position) {

    override fun openBlock() {
        Output.clickMineBlock()
        isOpened = true
    }
}
