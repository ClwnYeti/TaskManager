package com.example.taskmanager.services.exceptions

import org.springframework.http.HttpStatus
import java.util.UUID

class TaskException(message: String, val status: HttpStatus): Exception(message) {
    companion object {
        fun notFoundById(id: UUID): TaskException {
            return TaskException("No task with key $id", HttpStatus.NOT_FOUND)
        }
        fun notUniqueId(id: UUID): TaskException {
            return TaskException("Not unique key for tasks: $id", HttpStatus.CONFLICT)
        }
    }
}