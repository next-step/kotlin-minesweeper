package minesweeper.view.model

sealed interface BlockViewModel {

    override fun toString(): String
}

class HideBlockViewModel : BlockViewModel {

    override fun toString(): String = "□"
}

class MineBlockViewModel : BlockViewModel {

    override fun toString(): String = "♣"
}
