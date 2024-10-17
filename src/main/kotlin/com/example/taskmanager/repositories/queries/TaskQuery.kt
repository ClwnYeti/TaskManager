package com.example.taskmanager.repositories.queries

import com.example.taskmanager.domain.enums.TaskStatus

data class TaskQuery(
    val name: String?,
    val status: TaskStatus?
)
