package ru.job4j.dreamjob.store;

import org.junit.jupiter.api.Test;
import ru.job4j.dreamjob.Main;
import ru.job4j.dreamjob.model.Candidate;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CandidateDBStoreTest {

    @Test
    public void whenCreatePost() {
        CandidateDBStore store = new CandidateDBStore(new Main().loadPool());
        Candidate candidate = new Candidate(1, "name", "description", LocalDate.now());
        store.add(candidate);
        Candidate candidateInDB = store.findById(candidate.getId());
        assertThat(candidateInDB.getName(), is(candidate.getName()));
    }

    @Test
    public void whenUpdateCandidate() {
        CandidateDBStore store = new CandidateDBStore(new Main().loadPool());
        Candidate candidate = new Candidate(1, "updatedName", "description", LocalDate.now());
        store.update(candidate);
        Candidate candidateInDB = store.findById(candidate.getId());
        assertThat(candidateInDB.getName(), is(candidate.getName()));
    }

    @Test
    public void whenUpdateCandidatePhotoBytes() {
        CandidateDBStore store = new CandidateDBStore(new Main().loadPool());
        Candidate candidate = new Candidate(1, "updatedName", "description", LocalDate.now(),
                new byte[]{0, 12, 33, -127, 127});
        store.update(candidate);
        Candidate candidateInDB = store.findById(candidate.getId());
        assertThat(candidateInDB.getPhoto(), is(candidate.getPhoto()));
    }
}
