package com.example.ClassRosterWebService.Controller;

import com.example.ClassRosterWebService.DAO.TeacherDao;
import com.example.ClassRosterWebService.Entity.Teacher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TeacherController {

    @Autowired
    TeacherDao teacherDao;

    @GetMapping("teachers")
    public String displayTeachers(Model model) {
        model.addAttribute("teachers", teacherDao.getAllTeachers());
        return "teachers";
    }

    @PostMapping("addTeacher")
    public String addTeacher(HttpServletRequest request) {
        Teacher teacher = new Teacher();
        teacher.setFirstName(request.getParameter("firstName"));
        teacher.setLastName(request.getParameter("lastName"));
        teacher.setSpecialty(request.getParameter("specialty"));

        teacherDao.addTeacher(teacher);
        return "redirect:/teachers";
    }

    @GetMapping("deleteTeacher")
    public String deleteTeacher(HttpServletRequest request) {
        teacherDao.deleteTeacherById(Integer.parseInt(request.getParameter("id")));
        return "redirect:/teachers";
    }

    // ✅ EDIT TEACHER
    @GetMapping("editTeacher")
    public String editTeacher(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        model.addAttribute("teacher", teacherDao.getTeacherById(id));
        return "editTeacher";
    }

    // ✅ UPDATE TEACHER
    @PostMapping("updateTeacher")
    public String updateTeacher(HttpServletRequest request) {
        Teacher teacher = new Teacher();
        teacher.setId(Integer.parseInt(request.getParameter("id")));
        teacher.setFirstName(request.getParameter("firstName"));
        teacher.setLastName(request.getParameter("lastName"));
        teacher.setSpecialty(request.getParameter("specialty"));

        teacherDao.updateTeacher(teacher);
        return "redirect:/teachers";
    }
}
