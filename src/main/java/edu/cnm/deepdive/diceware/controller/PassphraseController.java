package edu.cnm.deepdive.diceware.controller;

import edu.cnm.deepdive.diceware.service.PassphraseService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/passphrases")
@Validated
public class PassphraseController {

  private final PassphraseService service;

  @Autowired
  public PassphraseController(PassphraseService service) {
    this.service = service;
  }

  @PostMapping(path = "/generate", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<String> post(
    @Max(20)
    @Positive
    @RequestParam(defaultValue = "5")
    int length
  ) {
    return service.generate(length);
  }
}
