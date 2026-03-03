package edu.cnm.deepdive.diceware.controller;

import edu.cnm.deepdive.diceware.service.PassphraseService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import java.util.List;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/passphrases")
@Validated
public class PassphraseController {

  private final PassphraseService service;

  @Autowired
  public PassphraseController(PassphraseService service) {
    this.service = service;
  }

  @PostMapping(path = "/generate", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public List<String> post(
    @Max(20)
    @Positive
    @RequestParam(defaultValue = "5")
    int length
  ) {
    return service.generate(length);
  }

  @PostMapping(path = "/generate", produces = MediaType.TEXT_PLAIN_VALUE)
  @ResponseBody
  public String post(
    @Max(20)
    @Positive
    @RequestParam(defaultValue = "5")
    int length,
    @Length(max = 5)
    @RequestParam(defaultValue = " ")
    String delimiter
  ) {
    return String.join(delimiter, service.generate(length));
  }

  @GetMapping(path = "/passphrase")
  public String get(
    @Max(20)
    @Positive
    @RequestParam(defaultValue = "5")
    int length,
    Model model
  ) {
    model.addAttribute("words", service.generate(length));
    model.addAttribute("length", length);

    return "passphrase";
  }
}
