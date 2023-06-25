package pi.dev.realestate.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pi.dev.realestate.entities.BadWords;
import pi.dev.realestate.repositories.BadWordsRepository;
import pi.dev.realestate.services.interfaces.IBadWordsService;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BadWordsService implements IBadWordsService {

    private final BadWordsRepository badWordsRepository;

    @Autowired
    public BadWordsService(BadWordsRepository badWordsRepository) {
        this.badWordsRepository = badWordsRepository;
    }

    @Override
    public BadWords getBadWordsById(int id) {
        Optional<BadWords> optionalBadWords = badWordsRepository.findById(id);
        return optionalBadWords.orElse(null);
    }

    @Override
    public List<BadWords> getAllBadWords() {
        return badWordsRepository.findAll();
    }

    @Override
    public BadWords createBadWords(BadWords badWords) {
        return badWordsRepository.save(badWords);
    }

    @Override
    public BadWords updateBadWords(int id, BadWords badWords) {
        BadWords existingBadWords = getBadWordsById(id);
        if (existingBadWords != null) {
            existingBadWords.setWord(badWords.getWord());
            return badWordsRepository.save(existingBadWords);
        }
        return null;
    }

    @Override
    public void deleteBadWords(int id) {
        badWordsRepository.deleteById(id);
    }
}
