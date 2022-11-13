package com.spacecodee.library_book_backend.service.people;

import org.springframework.stereotype.Service;

@Service
public class PeopleServiceImpl {

    private final PeopleService peopleService;

    public PeopleServiceImpl(PeopleService peopleService) {this.peopleService = peopleService;}

    public boolean existByNumberPhone(int numberPhone) {
        return this.peopleService.existByNumberPhone(numberPhone);
    }
}
