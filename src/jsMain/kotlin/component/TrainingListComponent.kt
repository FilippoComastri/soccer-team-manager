package component

import Match
import Training
import csstype.FlexGrow
import csstype.px
import kotlinext.js.jso
import mui.material.Box
import mui.material.ListItem
import mui.material.List
import react.Props

import react.fc

external interface TrainingListProps : Props {
    var trainings: List<Training>
}

val trainingList = fc<TrainingListProps> { props ->
    Box {
        attrs {
            sx = jso {
                maxHeight = 400.px
                flexGrow = FlexGrow(5.0)
            }

        }

        List {
            for (t in props.trainings) {
                ListItem {
                    child(trainingView) {
                        attrs {
                            training = t
                        }
                    }
                }

            }
        }
    }


}