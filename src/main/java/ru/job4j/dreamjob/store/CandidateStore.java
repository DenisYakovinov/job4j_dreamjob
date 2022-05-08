package ru.job4j.dreamjob.store;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.Candidate;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
@ThreadSafe
public class CandidateStore {

    private final AtomicInteger idCounter = new AtomicInteger();
    private final Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();

    private CandidateStore() {
        candidates.put(1, new Candidate(1, "Jon Doe", "middle", LocalDate.now()));
        candidates.put(2, new Candidate(2, "Foo Bar", "junior", LocalDate.now()));
        candidates.put(3, new Candidate(3, "Uncle Bob", "senior", LocalDate.now()));
        idCounter.addAndGet(candidates.size());
    }

    public void create(Candidate candidate) {
        candidate.setId(idCounter.incrementAndGet());
        candidates.putIfAbsent(candidate.getId(), candidate);
    }

    public void update(Candidate candidate) {
        candidates.put(candidate.getId(), candidate);
    }

    public Candidate findById(int id) {
        return candidates.get(id);
    }

    public Collection<Candidate> findAll() {
        return candidates.values();
    }
}
