package ru.job4j.dreamjob.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.store.CandidateDBStore;

import java.util.Collection;

@Service
@ThreadSafe
public class CandidateService {

    private final CandidateDBStore candidateStore;

    private CandidateService(CandidateDBStore candidateStore) {
        this.candidateStore = candidateStore;
    }

   public void create(Candidate candidate) {
        candidateStore.add(candidate);
    }

    public void update(Candidate candidate) {
        candidateStore.update(candidate);
    }

    public Candidate findById(int id) {
        return candidateStore.findById(id);
    }

    public Collection<Candidate> findAll() {
        return candidateStore.findAll();
    }
}
