package ru.job4j.dreamjob.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.service.CandidateService;

import java.time.LocalDate;

@Controller
public class CandidateController {
      private final CandidateService candidateService = CandidateService.getInstance();

      @GetMapping("/candidates")
      public String candidates(Model model) {
            model.addAttribute("candidates", candidateService.findAll());
            return "candidates";
      }

      @GetMapping("/formAddCandidate")
      public String addCandidate(Model model) {
            model.addAttribute("candidate", new Candidate(0, "Fill the field", "fill the field", LocalDate.now()));
            return "addCandidate";
      }

      @PostMapping("/createCandidate")
      public String createCandidate(@ModelAttribute Candidate candidate) {
            candidate.setCreated(LocalDate.now());
            candidateService.create(candidate);
            return "redirect:/candidates";
      }

      @GetMapping("/formUpdateCandidate/{candidateID}")
      public String formUpdateCandidate(Model model, @PathVariable int candidateID) {
            model.addAttribute("candidate", candidateService.findById(candidateID));
            return "/updateCandidate";
      }

      @PostMapping("/updateCandidate")
      public String updateCandidate(@ModelAttribute Candidate candidate) {
            candidateService.update(candidate);
            return "redirect:/candidates";
      }
}
