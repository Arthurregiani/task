package br.edu.ifsp.dmo.tasks.dto

data class TaskDto(
    val id: Long,
    val description: String,
    val isCompleted: Boolean
)