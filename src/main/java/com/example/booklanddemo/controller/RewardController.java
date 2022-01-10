package com.example.booklanddemo.controller;


import com.example.booklanddemo.domain.Reward;
import com.example.booklanddemo.repository.RewardRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class RewardController {
    private RewardRepository repository;

    public RewardController(RewardRepository repository) {
        this.repository = repository;
    }

    @RequestMapping("/rewards")
    public Reward saveReward(@RequestBody Reward reward){
        return repository.save(reward);
    }

    @GetMapping("/rewards")
    @ResponseStatus(HttpStatus.OK)
    public List<Reward> findAllRewards() {
        return repository.findAll();
    }

    @GetMapping("/rewards/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Reward findRewardById(@PathVariable Integer id) {

        Reward reward = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reward not found with id = " + id));

        return reward;
    }

    @PutMapping("/rewards/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Reward updateReward(@PathVariable ("id") Integer rewardId, @RequestBody Reward reward){

        return repository.findById(rewardId).map(entity -> {
            entity.setName(reward.getName());
            entity.setDescription(reward.getDescription());
            return repository.save(reward);
        })
                .orElseThrow(() -> new EntityNotFoundException("Car not found with id = " + rewardId));
    }

    @DeleteMapping("/rewards/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeRewardById(@PathVariable Integer id) {
        Reward reward = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reward not found with id = " + id));
        repository.delete(reward);
    }
}
