package auca.ac.rw.StudentRegistrationAPI.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import auca.ac.rw.StudentRegistrationAPI.modal.Student;


@RestController
@RequestMapping(value = "/api/students")
public class StudentController {
     List<Student> Students =  new ArrayList<>();

    public StudentController(){
        Students.add(new Student(1L, "Irera", "Jochebed", "jochebed@gmail.com", "Computer Science", 5.7));
        Students.add(new Student(2L, "Joyce", "Munezero", "munezero@gmail.com", "Nursing", 2.8));
        Students.add(new Student(3L, "Ineza", "Lidia", "Lidia@gmail.com", "Networking", 1.7));
        Students.add(new Student(4L, "Ndiku", "Josue", "josue@gmail.com", "Computer Science", 3.2));
        Students.add(new Student(5L, "Igisubizo", "Isaac", "isaac@gmail.com", "Bigdata", 4.8));
    }  
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents(){
        return new ResponseEntity<>(Students, HttpStatus.OK);
    }

    @GetMapping(value = "/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long studentId){

        Student foundStudent = null;
        for (Student student : Students){
            if(student.getStudentId().equals(studentId)){
                foundStudent = student;
                break;
            }

        }
        if (foundStudent != null){
          return new ResponseEntity<>(foundStudent, HttpStatus.OK);
        
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping(value = "/major/{major}")
    public ResponseEntity<List<Student>> getStudentsByMajor(@PathVariable String major){
        
        List<Student> matchingStudents = new ArrayList<>();
        
        for (Student student : Students){
            if(student.getMajor().equalsIgnoreCase(major)){
                matchingStudents.add(student);
            }
        }
        
        return new ResponseEntity<>(matchingStudents, HttpStatus.OK);
    }

    @GetMapping(value = "/filter")
    public ResponseEntity<List<Student>> filterStudentsByGpa(@RequestParam Double gpa){
    
    List<Student> filteredStudents = new ArrayList<>();
    
    for (Student student : Students){
        if(student.getGpa() >= gpa){
            filteredStudents.add(student);
        }
    }
    
    return new ResponseEntity<>(filteredStudents, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        
        // Auto-generate student ID
        Long newId;
        if (Students.isEmpty()) {
            newId = 1L;
        } else {
            // Get the last student's ID and add 1
            newId = Students.get(Students.size() - 1).getStudentId() + 1;
        }
        student.setStudentId(newId);
        
        // Add student to the list
        Students.add(student);
        
        return new ResponseEntity<>(student, HttpStatus.CREATED);  // 201 CREATED
    }

    @PutMapping(value = "/{studentId}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long studentId, @RequestBody Student updatedStudent) {
        
        // Find the student to update
        Student existingStudent = null;
        int index = -1;
        
        for (int i = 0; i < Students.size(); i++) {
            if (Students.get(i).getStudentId().equals(studentId)) {
                existingStudent = Students.get(i);
                index = i;
                break;
            }
        }
        
        // If student found, update their information
        if (existingStudent != null) {
            
            // Keep the same ID, update other fields
            updatedStudent.setStudentId(studentId);
            
            // Replace old student with updated student
            Students.set(index, updatedStudent);
            
            return new ResponseEntity<>(updatedStudent, HttpStatus.OK);  // 200 OK
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // 404 NOT FOUND
        }
    }

}
