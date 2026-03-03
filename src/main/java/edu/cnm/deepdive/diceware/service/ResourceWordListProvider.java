package edu.cnm.deepdive.diceware.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
class ResourceWordListProvider implements WordListProvider {

  private static final Pattern WORD_EXTRACTOR = Pattern.compile("^\\s*\\S+\\s+(\\S+)\\s*$");

  private final List<String> wordList;

  @Autowired
  ResourceWordListProvider(@Value("${diceware.wordList}") String wordListFile) throws IOException {
    Resource resource = new ClassPathResource(wordListFile);

    try (Stream<String> lines = Files.lines(Paths.get(resource.getURI()))) {
      wordList = lines.map(WORD_EXTRACTOR::matcher)
        .filter(Matcher::matches)
        .map((matcher) -> matcher.group(1))
        .filter((word) -> !word.isBlank())
        .distinct()
        .toList();
    }
  }

  @Override
  public List<String> getWordList() {
    return wordList;
  }
}
