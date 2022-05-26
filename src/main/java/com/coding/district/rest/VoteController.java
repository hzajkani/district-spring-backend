package com.coding.district.rest;

import com.coding.district.service.VoteService;
import com.coding.district.service.dto.VoteDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class VoteController {


    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @GetMapping("/votes")
    public Set<String> uniqueVoteNames() {
        return voteService.uniqueVoteNames();
    }

    @GetMapping("/votes/{name}")
    public List<VoteDTO> getVotesByNames(@PathVariable("name") String name) {
        return voteService.findVoteByName(name);
    }

}
