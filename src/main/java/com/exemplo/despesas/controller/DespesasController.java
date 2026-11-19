package com.exemplo.despesas.controller;

import com.exemplo.despesas.model.Categoria;
import com.exemplo.despesas.model.Despesa;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DespesasController {

    private final List<Categoria> categorias = new ArrayList<>();
    private final List<Despesa> despesas = new ArrayList<>();
    private int proximoCategoriaId = 1;
    private int proximoDespesaId = 1;

    @GetMapping("/")
    public String index(Model model) {
        double total = despesas.stream().mapToDouble(Despesa::getValor).sum();
        model.addAttribute("qtdCategorias", categorias.size());
        model.addAttribute("qtdDespesas", despesas.size());
        model.addAttribute("total", total);
        return "index";
    }

    // --- CATEGORIAS ---
    @GetMapping("/categorias")
    public String listarCategorias(Model model) {
        model.addAttribute("categorias", categorias);
        model.addAttribute("despesas", despesas);
        return "categorias";
    }

    @GetMapping("/categorias/nova")
    public String formCategoria() {
        return "categoria-form";
    }

    @PostMapping("/categorias")
    public String salvarCategoria(Categoria categoria) {
        categoria.setId(proximoCategoriaId++);
        categorias.add(categoria);
        return "redirect:/categorias";
    }

    // --- DESPESAS ---
    @GetMapping("/despesas")
    public String listarDespesas(Model model) {
        double total = despesas.stream().mapToDouble(Despesa::getValor).sum();
        model.addAttribute("despesas", despesas);
        model.addAttribute("total", total);
        return "despesas";
    }

    @GetMapping("/despesas/nova")
    public String formDespesa(Model model) {
        model.addAttribute("categorias", categorias);
        return "despesa-form";
    }

    @PostMapping("/despesas")
    public String salvarDespesa(Despesa despesa, @RequestParam int categoriaId) {
        Categoria cat = categorias.stream()
                .filter(c -> c.getId() == categoriaId)
                .findFirst()
                .orElse(null);

        despesa.setId(proximoDespesaId++);
        despesa.setCategoria(cat);
        despesas.add(despesa);
        return "redirect:/despesas";
    }

    @PostMapping("/despesas/excluir/{id}")
    public String excluirDespesa(@PathVariable int id) {
        despesas.removeIf(d -> d.getId() == id);
        return "redirect:/despesas";
    }
}