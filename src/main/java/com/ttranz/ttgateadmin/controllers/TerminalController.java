package com.ttranz.ttgateadmin.controllers;


import com.ttranz.ttgateadmin.dto.DtoTerminal;
import com.ttranz.ttgateadmin.models.Terminal;
import com.ttranz.ttgateadmin.repo.CounterRepository;
import com.ttranz.ttgateadmin.repo.TerminalRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

@Controller
public class TerminalController {


    private final TerminalRepository terminalRepository;
    private final CounterRepository counterRepository;

    public TerminalController(TerminalRepository terminalRepository, CounterRepository counterRepository) {
        this.terminalRepository = terminalRepository;
        this.counterRepository = counterRepository;
    }

    @GetMapping("/terminal-panel")
    public String getTerminalPanel(@PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC, size = 7) Pageable pageable, Model model){
        //Iterable<Terminal> terminals = terminalRepository.selectLastN();
        Page<DtoTerminal> page = terminalRepository.selectIntoDtoTerminal(pageable);

        model.addAttribute("page", page);
        return "blocks/terminal_block";
    }

    @PostMapping("/terminal-check")
    //public boolean terminalCheck(@RequestParam String terminal_tid){
    public ResponseEntity<Boolean> terminalCheck(@RequestParam String terminal_tid){
        return ResponseEntity.ok(terminalRepository.selectTerminalTIDCheck(terminal_tid));
    }



    @PostMapping("/terminal-add")
    public String terminalAdd(@PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC, size = 7) Pageable pageable, @RequestParam long terminal_org_id, @RequestParam String terminal_tid, @RequestParam String terminal_tsp, Model model){
        Terminal terminal = new Terminal(terminal_org_id, terminal_tid, terminal_tsp, 1);
        terminalRepository.save(terminal);
        counterRepository.counterTerminalPlus();

        Page<DtoTerminal> page = terminalRepository.selectIntoDtoTerminal(pageable);
        model.addAttribute("page", page);
        return "results/terminals_added";
    }

    @GetMapping("/terminal-edit-form")
    public String getTerminalEditForm(Long id, Model model){
        if(!terminalRepository.existsById(id)){
            return "/";
        }
        List<DtoTerminal> terminal = terminalRepository.searchTerminalForEdit(id);
        model.addAttribute("terminal", terminal);

        return "blocks/terminal_edit_form_block";
    }

    @PostMapping("/terminal-edit")
    public String terminalUpdate(@PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC, size = 7) Pageable pageable, @RequestParam long terminal_org_id, @RequestParam long id, @RequestParam String terminal_tid, @RequestParam String terminal_tsp, @RequestParam int terminal_status, Model model){
        Terminal terminal = terminalRepository.findTerminalById(id);
        terminal.setTerminal_org_id(terminal_org_id);
        terminal.setTerminal_tid(terminal_tid);
        terminal.setTerminal_tsp(terminal_tsp);
        terminal.setTerminal_status(terminal_status);
        terminalRepository.save(terminal);

        //return "results/ok";
        return getTerminalPanel(pageable, model);

    }

    @PostMapping("/terminal-delete")
    public String terminalDelete(@PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC, size = 7) Pageable pageable, @RequestParam long id, Model model) {
        Terminal terminal = terminalRepository.findTerminalById(id);
        terminalRepository.delete(terminal);
        counterRepository.counterTerminalMinus();

        //return "results/ok";
        //return getTerminalPanel(pageable, model);
        Page<DtoTerminal> page = terminalRepository.selectIntoDtoTerminal(pageable);
        model.addAttribute("page", page);
        return "results/terminals_added";

    }

    @PostMapping("/terminal-search")
    public String terminalSearch(@PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC, size = 100) Pageable pageable, @RequestParam String searchFor, Model model) {
        Page<DtoTerminal> page = terminalRepository.searchForTerminal(pageable, searchFor);
        model.addAttribute("page", page);
        return "results/terminals_added";

    }


    @GetMapping("terminal-list")
    public String terminalList(@RequestParam Long org_id, Model model){
        List<Terminal> terminals = terminalRepository.selectTerminalsByOrgId(org_id);
        model.addAttribute("terminals", terminals);

        return "results/terminal_list";
    }


//    @GetMapping("/terminal-show-all")
//    public String terminalShowAll(@PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable, Model model) {
//
//        //System.out.println(size);
//        Page<DtoTerminal> page = terminalRepository.selectIntoDtoTerminal(pageable);
//        model.addAttribute("page", page);
//        return "results/terminals_added";
//
//    }



}
