package com.example.swagger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

//    git remote add origin https://github.com/mayank-budbee/Swagger.git
//    git branch -M main
//    git push -u origin main

    echo "# Swagger" >> README.md
//    git init
//    git add README.md
//    git commit -m "first commit"
//    git branch -M main
//    git remote add origin https://github.com/mayank-budbee/Swagger.git
//    git push -u origin main

}
