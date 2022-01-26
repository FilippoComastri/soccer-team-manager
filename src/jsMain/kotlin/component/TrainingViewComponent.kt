package component

import Match
import Training
import csstype.*
import kotlinext.js.jso
import mui.material.Card
import mui.material.CardContent
import react.Props
import react.dom.html.ReactHTML
import react.fc
import mui.material.Typography
import mui.material.TypographyAlign

external interface TrainingProps : Props {
    var training: Training
}

val trainingView = fc<TrainingProps> { props ->
    Card {
        attrs {
            sx = jso {
                width = 100.pct
                maxHeight = 100.px
                backgroundColor = Color("LightGreen")
                color = Color("Black")
            }
        }
        CardContent {
            Typography {
                attrs {
                    gutterBottom = true
                    align = TypographyAlign.left
                    variant = "body1"
                    component = ReactHTML.div
                }
                + "Focus : ${props.training.focus} - ${props.training.dayHour}"
            }
            Typography {
                attrs {
                    gutterBottom = true
                    align = TypographyAlign.left
                    variant = "body2"
                    component = ReactHTML.div
                }
                + "${props.training.descrizione}"
            }
        }
    }
}