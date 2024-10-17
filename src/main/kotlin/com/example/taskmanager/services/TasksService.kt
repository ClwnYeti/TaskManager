package com.example.taskmanager.services

import com.example.taskmanager.domain.enums.TaskStatus
import com.example.taskmanager.domain.models.Task
import org.springframework.stereotype.Service
import java.util.UUID

interface TasksService {
    fun create(name: String, description: String): Task
    fun update(id: UUID, name: String?, description: String?, status: TaskStatus?): Task
    fun findById(id: UUID): Task?
    fun findByQuery(name: String?, status: TaskStatus?): Collection<Task>
}