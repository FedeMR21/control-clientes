package ar.com.g5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/clientes")
public class ClienteController {
    @Autowired
    private ClientesRepository clientesRepository;

    @GetMapping(value = "/agregar")
    public String agregarClientes(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "clientes/agregar_cliente";
    }

    @GetMapping(value = "/mostrar")
    public String mostrarclientes(Model model) {
        model.addAttribute("clientes", clientesRepository.findAll());
        return "clientes/ver_cliente";
    }

    @PostMapping(value = "/eliminar")
    public String eliminarCliente(@ModelAttribute Cliente cliente, RedirectAttributes redirectAttrs) {
        redirectAttrs
                .addFlashAttribute("mensaje", "Eliminado correctamente")
                .addFlashAttribute("clase", "warning");
        clientesRepository.deleteById(cliente.getId());
        return "redirect:/clientes/mostrar";
    }

    
    @PostMapping(value = "/editar/{id}")
    public String actualizarCliente(@ModelAttribute @Valid Cliente cliente, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            if (cliente.getId() != null) {
                return "clientes/editar_cliente";
            }
            return "redirect:/clientes/mostrar";
        }
        Cliente posibleClienteExistente = clientesRepository.findFirstById(cliente.getId());

        if (posibleClienteExistente != null && !posibleClienteExistente.getId().equals(cliente.getId())) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "Ya existe un cliente con ese id")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/clientes/agregar";
        }
        clientesRepository.save(cliente);
        redirectAttrs
                .addFlashAttribute("mensaje", "Editado correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/clientes/mostrar";
    }

    @GetMapping(value = "/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable int id, Model model) {
        model.addAttribute("cliente", clientesRepository.findById(id).orElse(null));
        return "clientes/editar_cliente";
    }

    @PostMapping(value = "/agregar")
    public String guardarCliente(@ModelAttribute @Valid Cliente cliente, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            return "clientes/agregar_cliente";
        }
        if (clientesRepository.findFirstById(cliente.getId()) != null) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "Ya existe un cliente con ese cliente")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/clientes/agregar";
        }
        clientesRepository.save(cliente);
        redirectAttrs
                .addFlashAttribute("mensaje", "Agregado correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/clientes/agregar";
    }
}
