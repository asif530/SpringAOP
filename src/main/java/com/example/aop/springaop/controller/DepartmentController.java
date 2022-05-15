package com.example.aop.springaop.controller;

import com.example.aop.springaop.model.Department;
import com.example.aop.springaop.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping
    public Department save( @RequestBody Department department ){
        return departmentService.save(department);
    }

    @GetMapping
    public String Test(){
        return "This is a bogus id";
    }

    /*
    * To get java.lang.IllegalStateException: Ambiguous mapping. error
     * */
    /*@GetMapping
    public String Test3(){
        return "This is a bogus id";
    }*/

    @PutMapping("/{id}")
    public Department Test1(@PathVariable(value = "id") Integer id){
        return departmentService.findById(id);
    }

    @PutMapping()
    public Department Test2(){
        return departmentService.findById(1);
    }

    @GetMapping("/{id}")
    public Department findById( @PathVariable(value = "id") Integer id ){
        return departmentService.findById(id);
    }

    @GetMapping("/name/{name}")
    public Department findByName( @PathVariable(value = "name") String name ){
        return departmentService.findByName(name);
    }
}
