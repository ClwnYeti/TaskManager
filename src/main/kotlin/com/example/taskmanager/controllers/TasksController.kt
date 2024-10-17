package com.example.taskmanager.controllers

import com.example.taskmanager.controllers.models.CreateTask
import com.example.taskmanager.controllers.models.UpdateTask
import com.example.taskmanager.domain.enums.TaskStatus
import com.example.taskmanager.domain.models.Task
import com.example.taskmanager.services.exceptions.TaskException
import com.example.taskmanager.services.TasksService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import java.lang.IllegalArgumentException
import java.util.UUID


@RestController
@RequestMapping("/Tasks")
class TasksController() {
    @Autowired
    private lateinit var service: TasksService

    @GetMapping("/{id}")
    fun findById(@PathVariable id: String): Task? {
        try {
            val uuid = UUID.fromString(id)
            return service.findById(uuid)
        } catch (e: IllegalArgumentException) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.message)
        }
    }

    @GetMapping
    fun findByQuery(
        @RequestParam(required = false) name: String?,
        @RequestParam(required = false) status: String?): Collection<Task> {
        try {
            val statusAsEnum = if (status == null) null else TaskStatus.valueOf(status)
            return service.findByQuery(name, statusAsEnum)
        } catch (e: IllegalArgumentException) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.message)
        }
    }

    @PostMapping
    fun create(@RequestBody createTask: CreateTask): Task {
        try {
            return service.create(createTask.name, createTask.description)
        } catch (e: TaskException) {
            throw ResponseStatusException(e.status, e.message)
        }
    }

    @PatchMapping("/{id}")
    fun patch(@PathVariable id: String, @RequestBody updateTask: UpdateTask): Task {
        try {
            val uuid = UUID.fromString(id)
            val statusAsEnum = if (updateTask.status == null) null else TaskStatus.valueOf(updateTask.status)
            return service.update(uuid, updateTask.name, updateTask.description, statusAsEnum)
        } catch (e: TaskException) {
            throw ResponseStatusException(e.status, e.message)
        }  catch (e: IllegalArgumentException) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.message)
        }
    }
}