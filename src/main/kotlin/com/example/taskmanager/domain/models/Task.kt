package com.example.taskmanager.domain.models

import com.example.taskmanager.domain.enums.TaskStatus
import java.util.UUID

class Task(val id: UUID, var name : String, var description: String, var status: TaskStatus) {
    constructor(id: UUID, name: String, description: String) : this(id, name, description, TaskStatus.Open)
}