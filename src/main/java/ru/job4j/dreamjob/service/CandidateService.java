package ru.job4j.dreamjob.service;

import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.store.CandidateStore;

import java.util.Collection;

@Service
public class CandidateService {

    private final CandidateStore candidateStore;

    private CandidateService(CandidateStore candidateStore) {
        this.candidateStore = candidateStore;
    }

    public void create(Candidate candidate) {
        candidateStore.create(candidate);
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
