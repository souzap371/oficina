package com.oficina.oficina.controller;


import com.oficina.oficina.model.Cliente;
import com.oficina.oficina.repository.ClienteRepository;
import com.oficina.oficina.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

 

    @GetMapping //("/clientes.html")
public String exibirClientesHtml(Model model) {
    // Certifique-se de que a lista de clientes está sendo passada
    List<Cliente> clientes = clienteService.listarTodos();
    model.addAttribute("clientes", clientes);
    return "clientes"; // Nome do arquivo HTML sem extensão
}

@GetMapping("/listarClientes")
public String listarClientes(Model model) {
    model.addAttribute("clientes", clienteService.listarTodos());
    return "clientes/lista";
}


    

    // Formulário para cadastrar um novo cliente
    @GetMapping("/novo")
    public String novoCliente(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "clienteForm";
    }

    // Salvar um novo cliente
    @PostMapping
    public String salvarCliente(@ModelAttribute Cliente cliente) {
        clienteService.salvar(cliente);
        return "redirect:/clientes";
    }

    // Visualizar os detalhes de um cliente
    @GetMapping("/{id}")
    public String visualizarCliente(@PathVariable Long id, Model model) {
        Cliente cliente = clienteService.buscarPorId(id);
        model.addAttribute("cliente", cliente);
        return "clientes/detalhes";
    }

    // Formulário para editar um cliente existente
    // @GetMapping("/editar/{id}")
    // public String editarCliente(@PathVariable Long id, Model model) {
    //     Cliente cliente = clienteService.buscarPorId(id);
    //     model.addAttribute("cliente", cliente);
    //     return "clientes/editar";
    // }
    @GetMapping("/editar/{id}")
    public String editarCliente(@PathVariable Long id, Model model) {
        Cliente cliente = clienteService.buscarPorId(id);
        
        if (cliente == null) {
            return "redirect:/clientes"; // Evita erro se o cliente não for encontrado
        }

        model.addAttribute("cliente", cliente);
        return "editarCliente";
    }


    // Atualizar um cliente
    @PostMapping("/editar")
    public String atualizarCliente(@ModelAttribute Cliente cliente) {
        clienteService.atualizar(cliente);
        return "redirect:/clientes";
    }

    // Deletar um cliente
    @GetMapping("/deletar/{id}")
    public String deletarCliente(@PathVariable Long id) {
        System.out.println("Tentando excluir cliente com ID: " + id);
        clienteService.deletar(id);
        return "redirect:/clientes";
    }
}
