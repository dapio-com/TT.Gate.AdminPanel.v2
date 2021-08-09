package com.ttranz.ttgateadmin.controllers;


import com.ttranz.ttgateadmin.dto.DtoTerminals;
import com.ttranz.ttgateadmin.models.Terminals;
import com.ttranz.ttgateadmin.repo.TerminalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class TerminalsController {


    @Autowired
    private TerminalRepository terminalRepository;

    @GetMapping("/terminal-panel")

    public String getTerminalPanel(@PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC, size = 7) Pageable pageable, Model model){
        //Iterable<Terminal> terminals = terminalRepository.selectLastN();
        Page<DtoTerminals> page = terminalRepository.selectIntoDtoTerminal(pageable);
        model.addAttribute("page", page);
        return "blocks/terminal_block";
    }

    @PostMapping("/terminal-check")
    public String terminalCheck(@RequestParam String terminal_tid){
        if(!terminalRepository.selectTerminalTIDCheck(terminal_tid)){
            return "results/false";
        } else {
            return "results/true";
        }
    }



    @PostMapping("/terminal-add")
    public String terminalAdd(@PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC, size = 7) Pageable pageable, @RequestParam long terminal_org_id, @RequestParam String terminal_tid, @RequestParam String terminal_tsp, Model model){
        Terminals terminal = new Terminals(terminal_org_id, terminal_tid, terminal_tsp, 1);
        terminalRepository.save(terminal);

        Page<DtoTerminals> page = terminalRepository.selectIntoDtoTerminal(pageable);
        model.addAttribute("page", page);
        return "results/terminals-added";
    }

    @GetMapping("/terminal-edit-form")
    public String getTerminalEditForm(long id, Model model){
        if(!terminalRepository.existsById(id)){
            return "/";
        }
        Optional<Terminals> terminal = terminalRepository.findById(id);
        ArrayList<Terminals> result = new ArrayList<>();
        terminal.ifPresent(result::add);
        model.addAttribute("terminal", result);
        return "blocks/terminal_edit_form_block";
    }

    @PostMapping("/terminal-edit")
    public String terminalUpdate(@RequestParam long terminal_org_id, @RequestParam long id, @RequestParam String terminal_tid, @RequestParam String terminal_tsp, @RequestParam int terminal_status, Model model){
        Terminals terminal = terminalRepository.findById(id).orElseThrow();
        terminal.setTerminal_org_id(terminal_org_id);
        terminal.setTerminal_tid(terminal_tid);
        terminal.setTerminal_tsp(terminal_tsp);
        terminal.setTerminal_status(terminal_status);
        terminalRepository.save(terminal);

        return "results/ok";
    }

    @PostMapping("/terminal-delete")
    public String terminalDelete(@RequestParam long id) {
        Terminals terminal = terminalRepository.findById(id).orElseThrow();
        terminalRepository.delete(terminal);

        return "results/ok";

    }

    @PostMapping("/terminal-search")
    public String terminalSearch(@PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC, size = 7) Pageable pageable, @RequestParam String searchFor, Model model) {
        Page<DtoTerminals> page = terminalRepository.searchForTerminal(pageable, searchFor);
        model.addAttribute("page", page);
        return "results/terminals-added";

    }

    @GetMapping("/terminal-show-all")
    public String terminalShowAll(@PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable, Model model) {

        Page<DtoTerminals> page = terminalRepository.selectIntoDtoTerminal(pageable);
        model.addAttribute("page", page);
        return "results/terminals-added";

    }



}
