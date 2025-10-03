package com.bezkoder.spring.restapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bezkoder.spring.restapi.model.Tutorial;

@Service
public class TutorialService {

  static List<Tutorial> tutorials = new ArrayList<Tutorial>();
  static long id = 0;
  
  public TutorialService(){
	  List<Tutorial> tuts = new ArrayList<Tutorial>();
	  tuts.add(new Tutorial(1, "The lion king", "This is a book about lion king", true));
	  tuts.add(new Tutorial(2, "The tiger king", "This is a book about tiger king", false));
	  tuts.add(new Tutorial(3, "The leopard king", "This is a book about leopard king", false));
	  tuts.add(new Tutorial(4, "The panther king", "This is a book about panther king", true));
	  this.tutorials = tuts;
  }

  public List<Tutorial> findAll() {
    return tutorials;
  }

  public List<Tutorial> findByTitleContaining(String title) {
    return tutorials.stream().filter(tutorial -> tutorial.getTitle().contains(title)).toList();
  }

  public Tutorial findById(long id) {
    return tutorials.stream().filter(tutorial -> id == tutorial.getId()).findAny().orElse(null);
  }

  public Tutorial save(Tutorial tutorial) {
    // update Tutorial
    if (tutorial.getId() != 0) {
      long _id = tutorial.getId();

      for (int idx = 0; idx < tutorials.size(); idx++)
        if (_id == tutorials.get(idx).getId()) {
          tutorials.set(idx, tutorial);
          break;
        }

      return tutorial;
    }

    // create new Tutorial
    tutorial.setId(++id);
    tutorials.add(tutorial);
    return tutorial;
  }

  public void deleteById(long id) {
    tutorials.removeIf(tutorial -> id == tutorial.getId());
  }

  public void deleteAll() {
    tutorials.removeAll(tutorials);
  }

  public List<Tutorial> findByPublished(boolean isPublished) {
    return tutorials.stream().filter(tutorial -> isPublished == tutorial.isPublished()).toList();
  }
}
