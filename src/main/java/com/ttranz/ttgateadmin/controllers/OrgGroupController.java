package com.ttranz.ttgateadmin.controllers;

import com.ttranz.ttgateadmin.dto.DtoOrgGroup;
import com.ttranz.ttgateadmin.models.OrgGroup;
import com.ttranz.ttgateadmin.repo.OrgGroupRepository;
import com.ttranz.ttgateadmin.repo.OrgRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
public class OrgGroupController {

    final OrgGroupRepository orgGroupRepository;
    final OrgRepository orgRepository;

    public OrgGroupController(OrgGroupRepository orgGroupRepository, OrgRepository orgRepository) {
        this.orgGroupRepository = orgGroupRepository;
        this.orgRepository = orgRepository;
    }


    @GetMapping("/org-group-panel")
    public String getOrgGroupPanel(Model model){
        List<OrgGroup> orgGroups = orgGroupRepository.selectLastN();
        model.addAttribute("org_groups", orgGroups);
        return "blocks/org_group_block";
    }

    @PostMapping("/org-group-check")
    public ResponseEntity<Boolean> groupCheck(@RequestParam String org_group_name){
        return ResponseEntity.ok(orgGroupRepository.selectOrgGroupNameCheck(org_group_name));
    }



    @PostMapping("/org-group-add")
    public String groupAdd(@RequestParam String org_group_name, String org_group_description, Model model){
        OrgGroup orgGroup = new OrgGroup(org_group_name, org_group_description);
        orgGroupRepository.save(orgGroup);

        List<OrgGroup> orgGroups = orgGroupRepository.selectLastN();
        model.addAttribute("org_groups", orgGroups);
        return "results/org_groups_added";
    }

    @GetMapping("/org-group-edit-form")
    public String getOrgGroupEditForm(long id, Model model){
        if(!orgGroupRepository.existsById(id)){
            return "/";
        }
        Optional<OrgGroup> orgGroup = orgGroupRepository.findById(id);
        ArrayList<OrgGroup> result = new ArrayList<>();
        orgGroup.ifPresent(result::add);
        model.addAttribute("org_group", result);
        return "blocks/org_group_edit_form_block";
    }

    @PostMapping("/org-group-edit")
    public String groupUpdate(@RequestParam long id, @RequestParam String org_group_name, @RequestParam String org_group_description, Model model){
        OrgGroup orgGroup = orgGroupRepository.findOrgGroupById(id);
        orgGroup.setOrg_group_name(org_group_name);
        orgGroup.setOrg_group_description(org_group_description);
        orgGroupRepository.save(orgGroup);

        //return "results/ok";
        return getOrgGroupPanel(model);
    }

    @PostMapping("/org-group-delete")
    public String groupDelete(@RequestParam long id, Model model) {
        if(orgRepository.countOrgsThatHaveGroups(id) <= 0){
            OrgGroup orgGroup = orgGroupRepository.findOrgGroupById(id);
            orgGroupRepository.delete(orgGroup);

            //return "results/ok";
            return getOrgGroupPanel(model);
        } else {
            return "results/denied";
        }


    }

    @PostMapping("/org-group-search")
    public String groupSearch(@RequestParam String searchFor, Model model) {
        Iterable<OrgGroup> orgGroups = orgGroupRepository.searchForOrgGroup(searchFor);
        model.addAttribute("org_groups", orgGroups);

        return "results/org_groups_added";

    }

    @GetMapping("/org-group-autocomplete")
    public ResponseEntity<List<DtoOrgGroup>> groupAutocomplete(@RequestParam String query) {
        List<DtoOrgGroup> orgGroups = orgGroupRepository.searchForOrgGroupAutocomplete(query);

        return ResponseEntity.ok(orgGroups);

    }



}
