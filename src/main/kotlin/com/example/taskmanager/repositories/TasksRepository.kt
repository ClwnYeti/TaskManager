package com.example.taskmanager.repositories

import com.example.taskmanager.domain.models.Task
import com.example.taskmanager.repositories.queries.TaskQuery
import org.springframework.stereotype.Repository
import java.util.UUID


interface TasksRepository {
    fun create(task: Task)
    fun put(task: Task)
    fun findById(id: UUID): Task?
    fun findByQuery(taskQuery: TaskQuery): Collection<Task>
}