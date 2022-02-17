package component.dialogs

import Training
import csstype.AlignItems
import csstype.Color
import csstype.JustifyContent
import kotlinext.js.jso
import mui.material.*
import mui.system.ResponsiveStyleValue
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.HTMLTextAreaElement
import react.Props
import react.ReactNode
import react.dom.onChange
import react.fc
import react.useState

external interface TrainingDialogProps: Props {
    var isOpenTrainDialog: Boolean
    var submitFormAddingTraining: (Training)->Unit
    var closeForm: ()->Unit
}

val trainingDialog = fc<TrainingDialogProps> { props ->

    var focusValue by useState("")
    var dateValue by useState("")
    var descValue by useState("")

    Dialog {
        attrs {
            open = props.isOpenTrainDialog
        }
        DialogTitle {
            +"Insert new Training"
        }
        DialogContent {
            Stack {
                attrs {
                    sx = jso {
                        justifyContent= JustifyContent.spaceAround
                        alignItems = AlignItems.stretch
                        spacing = ResponsiveStyleValue(0.5)
                    }
                    direction = ResponsiveStyleValue(StackDirection.column)
                }
                TextField {
                    attrs {
                        helperText = ReactNode("Focus")
                        variant = FormControlVariant.filled
                        onChange = {
                            focusValue = (it.target as HTMLInputElement).value
                        }
                    }
                }
                TextField {
                    attrs {
                        helperText = ReactNode("Date")
                        variant = FormControlVariant.filled
                        onChange = {
                            dateValue = (it.target as HTMLInputElement).value
                        }
                    }
                }
                TextField {
                    attrs {
                        helperText = ReactNode("Description")
                        variant = FormControlVariant.filled
                        multiline = true
                        minRows = 5
                        maxRows = 5
                        onChange = {
                            descValue = (it.target as HTMLTextAreaElement).value
                        }
                    }
                }
            }
        }
        DialogActions {
            Button {
                attrs {
                    onClick = { props.closeForm() }
                    style = jso() {
                        color = Color("#f2072e")
                        borderColor = Color("#f2072e")
                    }
                }
                +"Cancel"
            }
            Button {
                attrs {
                    onClick = {
                        props.submitFormAddingTraining(Training(dateValue,focusValue,descValue))
                    }
                    style = jso() {
                        color = Color("#20b02c")
                        borderColor = Color("#20b02c")
                    }
                }
                +"Submit"
            }
        }
    }
}