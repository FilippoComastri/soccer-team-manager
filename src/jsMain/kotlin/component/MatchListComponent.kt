package component

import Match
import component.dialogs.insertResultDialog
import csstype.FlexGrow
import kotlinext.js.jso
import react.Props
import react.fc
import mui.material.List
import mui.material.ListItem
import mui.material.Box
import csstype.px
import react.useState

external interface MatchListProps : Props {
    var updatematch: (Match) -> Unit
    var matches: List<Match>
    var myteam: String
}

val matchList = fc<MatchListProps> { props ->
    var isOpenChangeDialog by useState(false)
    var selectedMatch: Match? by useState(null)
    Box {
        attrs {
            sx = jso {
                maxHeight=400.px
                flexGrow = FlexGrow(5.0)
            }

        }
        List {
            for (m in props.matches) {
                ListItem {
                    child(matchView) {
                        attrs {
                            myTeam = props.myteam
                            match = m
                            openChangeDialog = {
                                selectedMatch = m
                                isOpenChangeDialog = true
                            }
                        }
                    }
                }

            }
        }
        selectedMatch?.let {  curr ->
            child(insertResultDialog) {
                attrs {
                    myTeam = props.myteam
                    match = curr
                    isOpenDialog = isOpenChangeDialog
                    closeForm = { isOpenChangeDialog=false }
                    submitForm = {
                            m -> props.updatematch(m)
                            isOpenChangeDialog = false
                    }
                }
            }
        }


    }


}