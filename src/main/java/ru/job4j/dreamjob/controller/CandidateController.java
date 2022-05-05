package ru.job4j.dreamjob.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.store.CandidateStore;

import java.time.LocalDate;

@Controller
public class CandidateController {
      private final CandidateStore candidateStore = CandidateStore.instOf();

      @GetMapping("/candidates")
      public String candidates(Model model) {
            model.addAttribute("candidates", candidateStore.findAll());
            return "candidates";
      }

      @GetMapping("/formAddCandidate")
      public String addCandidate(Model model) {
            model.addAttribute("candidate", new Candidate(0, "Fill the field", "fill the field", LocalDate.now()));
            return "addCandidate";
      }
}
