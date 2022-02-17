package component.HomePage

import Match
import Training
import addMatch
import addTraining
import component.dialogs.matchDialog
import component.dialogs.trainingDialog
import csstype.*
import deleteMatch
import deleteTraining
import kotlinext.js.jso
import kotlinx.coroutines.MainScope
import getMatchList
import getTrainingList
import kotlinx.coroutines.launch
import mui.material.*
import react.*
import updateMatch

private val scope = MainScope()

val myTeam = "Inter"

val home = fc<Props> {

    var matchlist by useState(emptyList<Match>())
    var traininglist by useState(emptyList<Training>())
    var isopenTrainingDialog by useState(false)
    var isopenMatchDialog by useState(false)

    useEffectOnce {
        scope.launch {
            matchlist = getMatchList()
            traininglist = getTrainingList()
        }
    }

    Box {
        attrs {
            sx = jso {
                display = Display.flex
                height = 800.px
                flexWrap = FlexWrap.nowrap
            }
        }

        child(menu) {
            attrs {
                openTrainingDialog = {
                    isopenTrainingDialog = true
                }
                openMatchDialog = {
                    isopenMatchDialog = true
                }
            }
        }
        child(matchList) {
            attrs {
                matches = matchlist
                myteam = myTeam
                updatematch = { match ->
                    scope.launch {
                        updateMatch(match)
                        matchlist = getMatchList()
                    }
                }
                onDelete = { match ->
                    scope.launch {
                        deleteMatch(match)
                        matchlist = getMatchList()
                    }
                }
            }
        }
        child(trainingList){
            attrs {
                trainings = traininglist
                onDelete = { training ->
                    scope.launch {
                        deleteTraining(training)
                        traininglist = getTrainingList()
                    }
                }
            }
        }
        child(trainingDialog) {
            attrs {
                isOpenTrainDialog = isopenTrainingDialog
                closeForm = { isopenTrainingDialog=false }
                submitFormAddingTraining = { training ->
                    scope.launch {
                        addTraining(training)
                        traininglist = getTrainingList()
                    }
                    isopenTrainingDialog = false
                }
            }

        }
        child(matchDialog) {
            attrs {
                isOpenDialog = isopenMatchDialog
                closeForm = { isopenMatchDialog = false }
                submitFormAddingMatch = { match ->
                    scope.launch {
                        addMatch(match)
                        matchlist = getMatchList()
                    }
                    isopenMatchDialog = false
                }
            }
        }
    }



}