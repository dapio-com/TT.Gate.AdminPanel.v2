package com.ttranz.ttgateadmin.controllers;


import com.ttranz.ttgateadmin.dto.DtoOrg;
import com.ttranz.ttgateadmin.dto.DtoOrgsAutocomplete;
import com.ttranz.ttgateadmin.models.Org;
import com.ttranz.ttgateadmin.repo.CounterRepository;
import com.ttranz.ttgateadmin.repo.OrgRepository;
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

import java.util.List;


@Controller
public class OrgController {

    private final OrgRepository orgRepository;
    private final TerminalRepository terminalRepository;
    private final CounterRepository counterRepository;

    public OrgController(OrgRepository orgRepository, TerminalRepository terminalRepository, CounterRepository counterRepository) {
        this.orgRepository = orgRepository;
        this.terminalRepository = terminalRepository;
        this.counterRepository = counterRepository;
    }

    @GetMapping("/org-panel")
    public String getOrgPanel(@PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC, size = 7) Pageable pageable, Model model){
        //Iterable<Terminal> terminals = terminalRepository.selectLastN();
        Page<DtoOrg> page = orgRepository.selectIntoDtoOrg(pageable);

        model.addAttribute("page", page);
        return "blocks/org_block";
    }


    @PostMapping("/org-check")
    public ResponseEntity<Boolean> orgCheck(@RequestParam String org_name){
        return ResponseEntity.ok(orgRepository.selectOrgNameCheck(org_name));
    }



    @PostMapping("/org-add")
    public String orgAdd(@PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC, size = 7) Pageable pageable, @RequestParam Long org_group_id, String org_name, String org_owner, Model model){
        Org org = new Org(org_group_id, org_name, org_owner);
        orgRepository.save(org);
        counterRepository.counterOrgPlus();

        Page<DtoOrg> page = orgRepository.selectIntoDtoOrg(pageable);
        model.addAttribute("page", page);
        return "results/orgs_added";
    }

    @GetMapping("/org-edit-form")
    public String getOrgEditForm(Long id, Model model){
        if(!orgRepository.existsById(id)){
            return "/";
        }
        List<DtoOrg> org = orgRepository.searchOrgForEdit(id);
        model.addAttribute("org", org);
        return "blocks/org_edit_form_block";
    }



//    @GetMapping("/terminal-edit-form")
//    public String getTerminalEditForm(Long id, Model model){
//        if(!terminalRepository.existsById(id)){
//            return "/";
//        }
//        List<DtoTerminal> terminal = terminalRepository.searchTerminalForEdit(id);
//        model.addAttribute("terminal", terminal);
//
//        return "blocks/terminal_edit_form_block";
//    }




    @PostMapping("/org-edit")
    public String orgUpdate(@PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC, size = 7) Pageable pageable, @RequestParam Long id, @RequestParam Long org_group_id, @RequestParam String org_name, @RequestParam String org_owner, Model model){


        Org org = orgRepository.findOrgById(id);
        org.setOrg_group_id(org_group_id);
        org.setOrg_name(org_name);
        org.setOrg_owner(org_owner);
        orgRepository.save(org);

        //return "results/ok";
        return getOrgPanel(pageable, model);
    }


    @PostMapping("/org-delete")
    public String orgDelete(@PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC, size = 7) Pageable pageable, @RequestParam long id, Model model) {
        if(terminalRepository.countTerminalsThatHaveOrgs(id) <= 0){
            Org org = orgRepository.findOrgById(id);
            orgRepository.delete(org);
            counterRepository.counterOrgMinus();

            //return "results/ok";
            return getOrgPanel(pageable, model);
        } else {
            return "results/denied";
        }

    }

    @PostMapping("/org-search")
    public String orgSearch(@PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC, size = 2000) Pageable pageable, @RequestParam String searchFor, Model model) {
//        Iterable<Org> orgs = orgRepository.searchForOrg(searchFor);
//        model.addAttribute("orgs", orgs);
        Page<DtoOrg> page = orgRepository.searchForOrg(pageable, searchFor);
        model.addAttribute("page", page);

        return "results/orgs_added";

    }


    @GetMapping("/org-autocomplete")
    public ResponseEntity<List<DtoOrgsAutocomplete>> orgAutocomplete(@RequestParam String query, Long orgGroupId) {
        List<DtoOrgsAutocomplete> orgs;

        if(orgGroupId > 0){
            orgs = orgRepository.searchForOrgAutocompleteOrgGroupId(query, orgGroupId);
        } else {
            orgs = orgRepository.searchForOrgAutocomplete(query);
        }


        return ResponseEntity.ok(orgs);

    }


    @GetMapping("org-list")
    public String orgList(@RequestParam Long org_group_id, Model model){
        List<Org> orgs = orgRepository.selectOrgsByOrgGroupId(org_group_id);
        model.addAttribute("orgs", orgs);

        return "results/org_list";
    }


    @GetMapping("/org-show-all")
    public String orgShowAll(Model model) {
        Iterable<Org> orgs = orgRepository.findAll();
        model.addAttribute("orgs", orgs);

        return "results/orgs_added";

    }

}
