package com.example.todolist.service;

import com.example.todolist.model.TodoEntity;
import com.example.todolist.model.TodoRequest;
import com.example.todolist.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class TodoService {
    private  final TodoRepository todoRepository;

    public TodoEntity add(TodoRequest request){
        TodoEntity todoEntity = new TodoEntity();
        todoEntity.setTitle(request.getTitle());
        todoEntity.setTodoOrder(request.getTodoOrder());
        todoEntity.setCompleted(request.getCompleted());
        return this.todoRepository.save(todoEntity);
    }

    public List<TodoEntity> searchAll(){
        return this.todoRepository.findAll();
    }

    public TodoEntity searchById(Long id){
        return this.todoRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
    public TodoEntity updateById(Long id, TodoRequest request){
        TodoEntity todoEntity = this.searchById(id);
        if (request.getTitle() != null){
            todoEntity.setTitle(request.getTitle());
        }
        if (request.getTodoOrder() != null){
            todoEntity.setTodoOrder(request.getTodoOrder());
        }
        if (request.getCompleted() != null){
            todoEntity.setCompleted(request.getCompleted());
        }
        return this.todoRepository.save(todoEntity);
    }
    public void deleteById(Long id){
        this.todoRepository.deleteById(id);
    }
    public void deleteAll(){
        this.todoRepository.deleteAll();
    }

}
