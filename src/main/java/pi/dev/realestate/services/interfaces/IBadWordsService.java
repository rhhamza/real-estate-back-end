package pi.dev.realestate.services.interfaces;

import pi.dev.realestate.entities.BadWords;

import java.util.List;

public interface IBadWordsService {
    BadWords getBadWordsById(int id);

    List<BadWords> getAllBadWords();

    BadWords createBadWords(BadWords badWords);

    BadWords updateBadWords(int id, BadWords badWords);

    void deleteBadWords(int id);
}

