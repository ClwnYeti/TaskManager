package com.example.taskmanager.controllers.models

import com.example.taskmanager.domain.enums.TaskStatus

data class UpdateTask(
    val name: String?,
    val description: String?,
    val status: String?
)