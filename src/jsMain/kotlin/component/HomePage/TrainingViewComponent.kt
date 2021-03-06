package component.HomePage

import Training
import csstype.*
import kotlinext.js.jso
import mui.icons.material.DeleteForever
import mui.material.*
import react.Props
import react.ReactNode
import react.dom.html.ReactHTML
import react.fc

external interface TrainingProps : Props {
    var training: Training
    var deleteTraining: () -> Unit
}

val trainingView = fc<TrainingProps> { props ->
    Card {
        attrs {
            sx = jso {
                width = 100.pct
                maxHeight = 180.px
                backgroundColor = Color("LightGreen")
                color = Color("Black")
            }
        }
        CardHeader {
            attrs {
                title= ReactNode("${props.training.focus}")
                subheader = ReactNode("${props.training.dayHour}")
            }
        }
        CardContent {
            Typography {
                attrs {
                    gutterBottom = true
                    align = TypographyAlign.left
                    variant = "body2"
                    component = ReactHTML.div
                }
                + "${props.training.descrizione}"
            }
            IconButton {
                attrs {
                    style = jso {
                        color = Color("#f2072e")
                        borderColor = Color("#f2072e")
                    }
                    onClick = {
                        props.deleteTraining()
                    }
                }
                DeleteForever()
            }
        }
    }
}