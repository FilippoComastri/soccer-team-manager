package component.HomePage

import Training
import csstype.FlexGrow
import csstype.px
import kotlinext.js.jso
import mui.material.*
import react.Props
import react.dom.html.ReactHTML

import react.fc

external interface TrainingListProps : Props {
    var trainings: List<Training>
    var onDelete : (Training) -> Unit
}

val trainingList = fc<TrainingListProps> { props ->
    Box {
        attrs {
            sx = jso {
                maxHeight = 400.px
                flexGrow = FlexGrow(5.0)
            }

        }
        Typography {
            attrs {
                sx = jso {
                    marginTop = 10.px
                }
                align = TypographyAlign.center
                variant = "h4"
                component = ReactHTML.div
            }
            +"Trainings"
        }
        List {
            for (t in props.trainings) {
                ListItem {
                    child(trainingView) {
                        attrs {
                            training = t
                            deleteTraining = { props.onDelete(t) }
                        }
                    }
                }

            }
        }
    }


}