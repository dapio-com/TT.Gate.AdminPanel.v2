package com.ttranz.ttgateadmin.controllers;


import com.ttranz.ttgateadmin.dto.DtoOrgsAutocomplete;
import com.ttranz.ttgateadmin.models.Org;
import com.ttranz.ttgateadmin.repo.OrgRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
public class OrgController {

    @Autowired
    private OrgRepository orgRepository;

    @GetMapping("/org-panel")
    public String getOrgPanel(Model model){
        Iterable<Org> orgs = orgRepository.selectLastN();
        model.addAttribute("orgs", orgs);
        return "blocks/org_block";
    }


    @PostMapping("/org-check")
    public String orgCheck(@RequestParam String org_name){
        if(!orgRepository.selectOrgNameCheck(org_name)){
            return "results/false";
        } else {
            return "results/true";
        }
    }



    @PostMapping("/org-add")
    public String orgAdd(@RequestParam String org_name, String org_owner, Model model){
        Org org = new Org(org_name, org_owner);
        orgRepository.save(org);

        Iterable<Org> orgs = orgRepository.selectLastN();
        model.addAttribute("orgs", orgs);
        return "results/orgs_added";
    }

    @GetMapping("/org-edit-form")
    public String getOrgEditForm(long id, Model model){
        if(!orgRepository.existsById(id)){
            return "/";
        }
        Optional<Org> org = orgRepository.findById(id);
        ArrayList<Org> result = new ArrayList<>();
        org.ifPresent(result::add);
        model.addAttribute("org", result);
        return "blocks/org_edit_form_block";
    }

    @PostMapping("/org-edit")
    public String orgUpdate(@RequestParam long id, @RequestParam String org_name, @RequestParam String org_owner, Model model){
        Org org = orgRepository.findById(id).orElseThrow();
        org.setOrg_name(org_name);
        org.setOrg_owner(org_owner);
        orgRepository.save(org);

        return "results/ok";
    }

    @PostMapping("/org-delete")
    public String orgDelete(@RequestParam long id) {
        Org org = orgRepository.findById(id).orElseThrow();
        orgRepository.delete(org);

        return "results/ok";

    }

    @PostMapping("/org-search")
    public String orgSearch(@RequestParam String searchFor, Model model) {
        Iterable<Org> orgs = orgRepository.searchForOrg(searchFor);
        model.addAttribute("orgs", orgs);

        return "results/orgs_added";

    }

    @GetMapping("/org-autocomplete")
    public ResponseEntity<List<DtoOrgsAutocomplete>> orgAutocomplete(@RequestParam String query) {
        List<DtoOrgsAutocomplete> orgs = orgRepository.searchForOrgAutocomplete(query);

        return ResponseEntity.ok(orgs);

    }

    @PostMapping("/org-show-all")
    public String orgShowAll(Model model) {
        Iterable<Org> orgs = orgRepository.findAll();
        model.addAttribute("orgs", orgs);

        return "results/orgs_added";

    }

}
