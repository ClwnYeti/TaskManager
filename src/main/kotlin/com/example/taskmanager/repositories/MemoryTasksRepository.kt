package com.example.taskmanager.repositories

import com.example.taskmanager.domain.models.Task
import com.example.taskmanager.services.exceptions.TaskException
import com.example.taskmanager.repositories.queries.TaskQuery
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class MemoryTasksRepository : TasksRepository {
    private val tasks: MutableMap<UUID, Task> = HashMap<UUID, Task>()
    override fun create(task: Task) {
        if (tasks.containsKey(task.id)) {
            throw TaskException.notUniqueId(task.id)
        }
        tasks[task.id] = task
    }

    override fun put(task: Task) {
        if (!tasks.containsKey(task.id)) {
            throw TaskException.notFoundById(task.id)
        }
        tasks[task.id] = task
    }

    override fun findById(id: UUID): Task? {
        return tasks[id]
    }

    override fun findByQuery(taskQuery: TaskQuery): Collection<Task> {
        return tasks.values
            .asSequence()
            .filter {
                taskQuery.name == null || it.name.contains(taskQuery.name)
            }
            .filter {
                taskQuery.status == null || it.status == taskQuery.status
            }
            .toList()
    }
}