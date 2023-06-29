package pi.dev.realestate.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pi.dev.realestate.entities.BadWords;
import pi.dev.realestate.services.interfaces.IBadWordsService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/badwords")
public class BadWordsCntroller {
    private final IBadWordsService badWordsService;

    @Autowired
    public BadWordsCntroller(IBadWordsService badWordsService) {
        this.badWordsService = badWordsService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BadWords> getBadWordsById(@PathVariable int id) {
        BadWords badWords = badWordsService.getBadWordsById(id);
        if (badWords != null) {
            return ResponseEntity.ok(badWords);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<BadWords>> getAllBadWords() {
        List<BadWords> badWordsList = badWordsService.getAllBadWords();
        return ResponseEntity.ok(badWordsList);
    }

    @PostMapping
    public ResponseEntity<BadWords> createBadWords(@RequestBody BadWords badWords) {
        BadWords createdBadWords = badWordsService.createBadWords(badWords);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBadWords);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BadWords> updateBadWords(@PathVariable int id, @RequestBody BadWords badWords) {
        BadWords updatedBadWords = badWordsService.updateBadWords(id, badWords);
        if (updatedBadWords != null) {
            return ResponseEntity.ok(updatedBadWords);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBadWords(@PathVariable int id) {
        badWordsService.deleteBadWords(id);
        return ResponseEntity.noContent().build();
    }
}

