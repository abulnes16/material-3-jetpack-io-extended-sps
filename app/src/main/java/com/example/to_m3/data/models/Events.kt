package com.example.to_m3.data.models

sealed class ToDoFormEvent {
    data class OnOpenModalEvent(val isModalOpen: Boolean): ToDoFormEvent()
    data class OnTitleEvent(val title: String): ToDoFormEvent()
    data class OnDescriptionEvent(val description: String): ToDoFormEvent()
    data class OnCategoryEvent(val category: String): ToDoFormEvent()
}

sealed class ToDoEvent {
    data class OnOpenDeleteDialog(val isDialogOpen: Boolean): ToDoEvent()
}