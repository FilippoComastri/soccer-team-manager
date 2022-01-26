package component.pages

import mui.material.Card
import mui.material.CardContent
import mui.material.Typography
import react.Props
import react.fc

val statSquad = fc<Props> {
    Card {
        CardContent {
            Typography {
                +"Squad"
            }
        }
    }
}