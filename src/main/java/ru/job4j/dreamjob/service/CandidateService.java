package ru.job4j.dreamjob.service;

import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.store.CandidateStore;

import java.util.Collection;

public class CandidateService {

    private final CandidateStore candidateStore = CandidateStore.instOf();

    private CandidateService() {
    }

    public static CandidateService getInstance() {
        return CandidateServiceHolder.INSTANCE;
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

    private static final class CandidateServiceHolder {

        private final static CandidateService INSTANCE = new CandidateService();
    }
}
