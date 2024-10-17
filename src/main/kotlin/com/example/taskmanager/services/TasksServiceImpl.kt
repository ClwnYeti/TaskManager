package com.example.taskmanager.services

import com.example.taskmanager.domain.enums.TaskStatus
import com.example.taskmanager.domain.models.Task
import com.example.taskmanager.repositories.TasksRepository
import com.example.taskmanager.repositories.queries.TaskQuery
import com.example.taskmanager.services.exceptions.TaskException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class TasksServiceImpl : TasksService {
    @Autowired
    private lateinit var taskRepository: TasksRepository
    override fun create(name: String, description: String): Task {
        val task = Task(UUID.randomUUID(), name, description)
        taskRepository.create(task)
        return task
    }

    override fun update(id: UUID, name: String?, description: String?, status: TaskStatus?): Task {
        val oldTask: Task = taskRepository.findById(id) ?: throw TaskException.notFoundById(id)
        val newTask = Task(
                oldTask.id,
                name ?: oldTask.name,
                description ?: oldTask.description,
                status ?: oldTask.status
            )
        taskRepository.put(newTask)
        return newTask
    }

    override fun findById(id: UUID): Task? {
        return taskRepository.findById(id)
    }

    override fun findByQuery(name: String?, status: TaskStatus?): Collection<Task> {
        val query = TaskQuery(name, status)
        return taskRepository.findByQuery(query)
    }
}