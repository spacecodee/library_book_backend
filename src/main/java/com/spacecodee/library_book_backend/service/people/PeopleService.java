package com.spacecodee.library_book_backend.service.people;

import com.spacecodee.library_book_backend.repository.IPeopleRepository;
import org.springframework.stereotype.Service;

@Service
public class PeopleService implements IPeopleService {

    private final IPeopleRepository iPeopleRepository;

    public PeopleService(IPeopleRepository iPeopleRepository) {this.iPeopleRepository = iPeopleRepository;}

    @Override
    public boolean existByNumberPhone(int numberPhone) {
        return this.iPeopleRepository.existsByPeoplePhone(numberPhone);
    }
}
