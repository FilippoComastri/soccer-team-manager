package component.ClassesImpl

import component.Player

data class PlayerImpl(
    override var color: String,
    override var name: String,
    override var numberColor: String,
    override var nameColor: String,
    override var number: Number,
   //override var onClick: Function<Nothing>
) : Player