package com.vidasana.sismed.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String inicio() {
        return "redirect:/dashboard";
    }

    @GetMapping("/pacientes")
    public String pacientes() {
        return "pacientes";
    }

    @GetMapping("/medicos")
    public String medicos() {
        return "medicos";
    }

    @GetMapping("/citas")
    public String citas() {
        return "citas";
    }

    @GetMapping("/atenciones")
    public String atenciones() {
        return "atenciones";
    }

    @GetMapping("/diagnosticos")
    public String diagnosticos() {
        return "diagnosticos";
    }

    @GetMapping("/pagos")
    public String pagos() {
        return "pagos";
    }
}